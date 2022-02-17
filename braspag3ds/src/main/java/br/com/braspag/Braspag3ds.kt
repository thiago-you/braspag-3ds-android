package br.com.braspag

import android.app.Activity
import android.content.Context
import android.util.Log
import br.com.braspag.customization.CustomButton
import br.com.braspag.customization.CustomLabel
import br.com.braspag.customization.CustomTextBox
import br.com.braspag.customization.CustomToolbar
import br.com.braspag.data.*
import br.com.braspag.internal.cardinal.CardinalHelper
import br.com.braspag.internal.data.*
import br.com.braspag.internal.extensions.toRequestOrder
import br.com.braspag.internal.extensions.toRequestValidate
import br.com.braspag.internal.network.BraspagClient
import br.com.braspag.internal.network.dto.Authentication
import br.com.braspag.internal.network.dto.RequestOrder
import br.com.braspag.internal.network.dto.RequestValidate
import com.auth0.jwt.JWT
import com.cardinalcommerce.shared.userinterfaces.UiCustomization
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class Braspag3ds(environment: Environment = Environment.SANDBOX) {

    private val TAG = "Braspag3ds"

    private lateinit var accessToken: String
    private val staging: Environment = environment
    private val braspagClient: BraspagClient = BraspagClient(environment)
    private val cardinal: CardinalHelper = CardinalHelper(environment)
    private lateinit var callback: (authResponse: AuthenticationResponse) -> Unit
    private val uiCustomization = UiCustomization()

    fun customize(
        toolbarCustomization: CustomToolbar? = null,
        textBoxCustomization: CustomTextBox? = null,
        labelCustomization: CustomLabel? = null,
        buttons: List<CustomButton> = listOf()
    ) {
        if (toolbarCustomization != null)
            uiCustomization.toolbarCustomization = toolbarCustomization

        if (textBoxCustomization != null)
            uiCustomization.textBoxCustomization = textBoxCustomization

        if (labelCustomization != null)
            uiCustomization.labelCustomization = labelCustomization

        for (button in buttons) {
            uiCustomization.setButtonCustomization(button, button.type.value)
        }
    }

    fun authenticate(
        accessToken: String,
        orderData: OrderData,
        cardData: CardData,
        giftCard: GiftCardData? = null,
        authOptions: OptionsData? = null,
        billToData: BillToData? = null,
        shipToData: ShipToData? = null,
        cart: List<CartItemData>? = null,
        deviceData: List<DeviceData>? = null,
        userData: UserData? = null,
        airlineData: AirlineData? = null,
        recurringData: RecurringData? = null,
        mddData: MddData? = null,
        ipAddress: String? = null,
        activity: Activity,
        callback: (authResponse: AuthenticationResponse) -> Unit
    ) {
        this.callback = callback
        this.accessToken = accessToken

        val order = orderData.toRequestOrder()

        AppCenter.start(
            activity.application, BuildConfig.APP_CENTER_KEY,
            Analytics::class.java, Crashes::class.java
        )
        // INIT
        configAppCenter(accessToken, staging)

        initCardinal(activity, order, uiCustomization) { isSuccessful, message ->
            if (isSuccessful) {

                val enrollData =
                    EnrollData.createInstance(
                        orderData,
                        cardData,
                        giftCard,
                        authOptions,
                        billToData,
                        shipToData,
                        cart,
                        deviceData,
                        userData,
                        airlineData,
                        mddData,
                        recurringData,
                        ipAddress
                    )

                // ENROLL
                enroll(enrollData, accessToken) { enrollResult ->
                    when (enrollResult.status) {

                        EnrollStatus.ENROLLED -> {
                            Log.i(TAG, "ENROLLED: $message, paReq: ${enrollResult.paReq}")

                            if (enrollData.authSuppressChallenge == true) {
                                callback.invoke(
                                    AuthenticationResponse(
                                        status = AuthenticationResponseStatus.CHALLENGE_SUPPRESSION,
                                        returnCode = "MPI601",
                                        returnMessage = "Challenge suppressed"
                                    )
                                )

                            } else {

                                if (enrollResult.transactionId == null) {
                                    callback.invoke(
                                        AuthenticationResponse(
                                            status = AuthenticationResponseStatus.ERROR,
                                            returnCode = "MSI667",
                                            returnMessage = "transactionId is null"
                                        )
                                    )
                                } else {

                                    // CCACONTINUE
                                    ccaContinue(
                                        activity,
                                        enrollResult.transactionId,
                                        enrollResult.paReq!!
                                    ) {
                                        Log.i(TAG, ":::::::::::::: ccaContinue return: $it")

                                        when (it) {
                                            ActionCode.SUCCESS,
                                            ActionCode.NO_ACTION,
                                            ActionCode.FAILURE,
                                            ActionCode.ERROR -> {

                                                // VALIDATE

                                                validate(
                                                    enrollData.toRequestValidate(enrollResult.transactionId),
                                                    validateCallback
                                                )
                                            }
                                            else -> {
                                                callback.invoke(
                                                    AuthenticationResponse(
                                                        status = AuthenticationResponseStatus.ERROR,
                                                        returnCode = "MPI902",
                                                        returnMessage = "Unexpected authentication response"
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        EnrollStatus.VALIDATION_NEEDED -> {
                            if (enrollResult.transactionId == null) {
                                callback.invoke(
                                    AuthenticationResponse(
                                        status = AuthenticationResponseStatus.ERROR,
                                        returnCode = "MSI667",
                                        returnMessage = "transactionId is null"
                                    )
                                )
                            } else {
                                // VALIDATE
                                validate(
                                    enrollData.toRequestValidate(enrollResult.transactionId),
                                    validateCallback
                                )
                            }
                        }

                        EnrollStatus.AUTHENTICATION_CHECK_NEEDED -> {
                            if (enrollResult.authentication == null) {
                                callback.invoke(
                                    AuthenticationResponse(
                                        status = AuthenticationResponseStatus.ERROR,
                                        returnCode = "MSI668",
                                        returnMessage = "authentication is null"
                                    )
                                )
                            } else {
                                checkAuthentication(enrollResult.authentication, callback)
                            }
                        }

                        EnrollStatus.NOT_ENROLLED -> {
                            callback.invoke(
                                AuthenticationResponse(
                                    status = AuthenticationResponseStatus.UNENROLLED,
                                    xId = enrollResult.xid,
                                    eci = enrollResult.eci,
                                    version = enrollResult.version,
                                    referenceId = enrollResult.referenceId,
                                    returnCode = enrollResult.authentication?.returnCode,
                                    returnMessage = enrollResult.authentication?.returnMessage
                                )
                            )
                        }

                        EnrollStatus.FAILED -> {
                            callback.invoke(
                                AuthenticationResponse(
                                    status = AuthenticationResponseStatus.FAILURE,
                                    xId = enrollResult.xid,
                                    eci = enrollResult.eci,
                                    version = enrollResult.version,
                                    referenceId = enrollResult.referenceId,
                                    returnCode = enrollResult.authentication?.returnCode,
                                    returnMessage = enrollResult.authentication?.returnMessage
                                )
                            )
                        }

                        EnrollStatus.UNSUPPORTED_BRAND -> {
                            callback.invoke(
                                AuthenticationResponse(
                                    status = AuthenticationResponseStatus.UNSUPPORTED_BRAND,
                                    returnCode = enrollResult.authentication?.returnCode,
                                    returnMessage = enrollResult.authentication?.returnMessage
                                )
                            )
                        }

                        else -> {
                            callback.invoke(
                                AuthenticationResponse(
                                    status = AuthenticationResponseStatus.ERROR,
                                    returnCode = "MPI906",
                                    returnMessage = "An error has occurred during enroll"
                                )
                            )
                        }
                    }
                }


            } else {
                callback.invoke(
                    AuthenticationResponse(
                        status = AuthenticationResponseStatus.ERROR,
                        returnCode = "MPI906",
                        returnMessage = "An error has occurred during init"
                    )
                )
            }
        }
    }

    private fun initCardinal(
        activity: Context,
        order: RequestOrder,
        uiCustomization: UiCustomization,
        callback: (isSuccessful: Boolean, message: String) -> Unit
    ) {
        braspagClient.getJwt(order, accessToken) {
            Log.i(TAG, ":::: getJwt response $it")
            if (it.result?.token != null) {
                cardinal.cardinalInit(
                    activity,
                    it.result.token,
                    uiCustomization
                ) { isInitSuccessul, msg ->
                    callback.invoke(isInitSuccessul, msg)
                }
            } else {
                callback.invoke(false, it.errorMessage.toString())
            }
        }
    }

    private fun enroll(
        request: EnrollData,
        oauthToken: String,
        callback: (enrollResult: EnrollResult) -> Unit
    ) {
        braspagClient.enroll(request, oauthToken) { clientResult ->
            clientResult.result?.let { response ->
                var eci = response.authentication.eci

                val status = response.status
                val enrollStatus: EnrollStatus

                when (status) {
                    EnrollStatus.UNSUPPORTED_BRAND.name -> {
                        enrollStatus = EnrollStatus.UNSUPPORTED_BRAND
                    }
                    EnrollStatus.ENROLLED.name -> {
                        // should display challenge
                        enrollStatus = EnrollStatus.ENROLLED
                    }
                    EnrollStatus.VALIDATION_NEEDED.name -> {
                        enrollStatus = EnrollStatus.VALIDATION_NEEDED
                    }
                    EnrollStatus.AUTHENTICATION_CHECK_NEEDED.name -> {
                        enrollStatus = EnrollStatus.AUTHENTICATION_CHECK_NEEDED
                    }
                    EnrollStatus.NOT_ENROLLED.name -> {
                        enrollStatus = EnrollStatus.NOT_ENROLLED
                    }
                    EnrollStatus.FAILED.name -> {
                        enrollStatus = EnrollStatus.FAILED
                        if (eci == null) eci = response.authentication.eciRaw
                    }
                    else -> {
                        // error
                        enrollStatus = EnrollStatus.ERROR
                    }
                }

                callback.invoke(
                    EnrollResult(
                        enrollStatus,
                        response.authentication,
                        response.authentication.returnMessage ?: "",
                        response.authenticationTransactionId,
                        response.paReq,
                        response.authentication.xId,
                        eci,
                        response.authentication.cavv,
                        response.authentication.version,
                        response.authentication.transactionId
                    )
                )
            } ?: callback.invoke(
                EnrollResult(
                    status = EnrollStatus.NOT_ENROLLED,
                    message = clientResult.errorMessage ?: "Not enrolled"
                )
            )
        }
    }

    private fun validate(
        request: RequestValidate,
        callback: (isValidateSuccessful: Boolean, data: Authentication?) -> Unit
    ) {
        braspagClient.validate(request, accessToken) {
            if (it.result?.status != null) {
                callback.invoke(true, it.result)
            } else {
                val errorResponse = Authentication(
                    returnCode = "MPI900",
                    returnMessage = "An error has occurred (${it.statusCode.code})"
                )
                callback.invoke(false, errorResponse)
            }
        }
    }

    private fun ccaContinue(
        currentActivity: Activity,
        transactionId: String,
        payload: String,
        callback: (ActionCode) -> Unit
    ) {
        cardinal.cardinalCcaContinue(currentActivity, transactionId, payload) {
            callback.invoke(cardinal.convertToActionCode(it.actionCode))
        }
    }

    private fun checkAuthentication(
        authentication: Authentication,
        callback: (authResponse: AuthenticationResponse) -> Unit
    ) {

        Log.i(TAG, "::::: checkAuthentication - authentication: $authentication")

        when (authentication.status) {

            AuthenticationStatus.AUTHENTICATED.name -> {
                callback.invoke(
                    AuthenticationResponse(
                        AuthenticationResponseStatus.SUCCESS,
                        authentication.cavv,
                        authentication.xId,
                        authentication.eci,
                        authentication.version,
                        authentication.transactionId,
                        authentication.returnCode,
                        authentication.returnMessage
                    )
                )
            }

            AuthenticationStatus.UNAVAILABLE.name -> {
                callback.invoke(
                    AuthenticationResponse(
                        status = AuthenticationResponseStatus.UNENROLLED,
                        xId = authentication.xId,
                        eci = authentication.eci,
                        version = authentication.version,
                        referenceId = authentication.transactionId,
                        returnCode = authentication.returnCode,
                        returnMessage = authentication.returnMessage
                    )
                )
            }

            AuthenticationStatus.FAILED.name -> {
                callback.invoke(
                    AuthenticationResponse(
                        AuthenticationResponseStatus.FAILURE,
                        xId = authentication.xId,
                        eci = authentication.eci ?: authentication.eciRaw,
                        version = authentication.version,
                        referenceId = authentication.transactionId,
                        returnCode = authentication.returnCode,
                        returnMessage = authentication.returnMessage
                    )
                )
            }

            AuthenticationStatus.ERROR_OCCURRED.name -> {
                callback.invoke(
                    AuthenticationResponse(
                        status = AuthenticationResponseStatus.ERROR,
                        xId = authentication.xId,
                        eci = authentication.eci ?: authentication.eciRaw,
                        returnCode = authentication.returnCode,
                        returnMessage = authentication.returnMessage,
                        referenceId = authentication.transactionId
                    )
                )
            }

            else -> {
                callback.invoke(
                    AuthenticationResponse(
                        status = AuthenticationResponseStatus.ERROR,
                        xId = authentication.xId,
                        eci = authentication.eci ?: authentication.eciRaw,
                        returnCode = authentication.returnCode,
                        returnMessage = authentication.returnMessage,
                        referenceId = authentication.transactionId
                    )
                )
            }
        }
    }

    private val validateCallback: (isSuccessful: Boolean, data: Authentication?) -> Unit =
        { isSuccessful, data ->

            Log.i(TAG, "------ validateCallback - $isSuccessful - return: $data")

            try {
                if (data != null) {
                    checkAuthentication(data, callback)
                } else {
                    callback.invoke(
                        AuthenticationResponse(
                            status = AuthenticationResponseStatus.ERROR,
                            returnCode = "MSI666",
                            returnMessage = "authentication is null"
                        )
                    )
                }

            } catch (t: Throwable) {
                Log.e(TAG, "::::: ERROR on validateCallback - $t")
            }
            cardinal.cardinalCleanupInstance()
        }
    private fun configAppCenter(JWTEncoded: String, environment: Environment) {

        val jwt = JWT().decodeJwt(JWTEncoded)
        val clientId = jwt.getClaim("client_id").asString()
        val clientName = jwt.getClaim("client_name").asString()

        val properties = HashMap<String, String>()
        properties["clientId"] = clientId
        properties["clientName"] = clientName

        when (environment) {
            Environment.SANDBOX -> {
                Analytics.trackEvent("SANDBOX", properties)
            }
            Environment.PRODUCTION -> {
                Analytics.trackEvent("PRODUCTION", properties)
            }
        }
    }
}