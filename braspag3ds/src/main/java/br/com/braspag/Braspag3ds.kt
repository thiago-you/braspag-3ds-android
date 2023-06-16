package br.com.braspag

import android.app.Activity
import android.content.Context
import android.util.Log
import br.com.braspag.app.BuildConfig
import br.com.braspag.customization.CustomButton
import br.com.braspag.customization.CustomLabel
import br.com.braspag.customization.CustomTextBox
import br.com.braspag.customization.CustomToolbar
import br.com.braspag.data.AirlineData
import br.com.braspag.data.AuthenticationResponse
import br.com.braspag.data.AuthenticationResponseStatus
import br.com.braspag.data.BillToData
import br.com.braspag.data.CardData
import br.com.braspag.data.CartItemData
import br.com.braspag.data.DeviceData
import br.com.braspag.data.Environment
import br.com.braspag.data.GiftCardData
import br.com.braspag.data.MddData
import br.com.braspag.data.OptionsData
import br.com.braspag.data.OrderData
import br.com.braspag.data.RecurringData
import br.com.braspag.data.ShipToData
import br.com.braspag.data.UserData
import br.com.braspag.internal.cardinal.CardinalHelper
import br.com.braspag.internal.components.BraspagJwt
import br.com.braspag.internal.data.ActionCode
import br.com.braspag.internal.data.AuthenticationStatus
import br.com.braspag.internal.data.EnrollData
import br.com.braspag.internal.data.EnrollResult
import br.com.braspag.internal.data.EnrollStatus
import br.com.braspag.internal.extensions.toRequestOrder
import br.com.braspag.internal.extensions.toRequestValidate
import br.com.braspag.internal.network.BraspagClient
import br.com.braspag.internal.network.dto.Authentication
import br.com.braspag.internal.network.dto.RequestOrder
import br.com.braspag.internal.network.dto.RequestValidate
import com.cardinalcommerce.shared.userinterfaces.UiCustomization
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class Braspag3ds(private val environment: Environment = Environment.SANDBOX) {

    companion object {
        private const val TAG = "Braspag3ds"
    }

    private lateinit var accessToken: String
    private lateinit var callback: (authResponse: AuthenticationResponse) -> Unit

    private val uiCustomization = UiCustomization()
    private val cardinal: CardinalHelper = CardinalHelper(environment)
    private val braspagClient: BraspagClient = BraspagClient(environment)

    fun customize(
        toolbarCustomization: CustomToolbar? = null,
        textBoxCustomization: CustomTextBox? = null,
        labelCustomization: CustomLabel? = null,
        buttons: List<CustomButton> = listOf(),
    ) {
        if (toolbarCustomization != null) {
            uiCustomization.toolbarCustomization = toolbarCustomization
        }
        if (textBoxCustomization != null) {
            uiCustomization.textBoxCustomization = textBoxCustomization
        }
        if (labelCustomization != null) {
            uiCustomization.labelCustomization = labelCustomization
        }

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
        callback: (authResponse: AuthenticationResponse) -> Unit,
    ) {
        this.callback = callback
        this.accessToken = accessToken

        val order = orderData.toRequestOrder()

        setupAppCenterTrack(activity, accessToken, environment)

        initCardinal(activity, order, uiCustomization) { isSuccessful, message ->
            if (!isSuccessful) {
                return@initCardinal callback(
                    AuthenticationResponse(
                        status = AuthenticationResponseStatus.ERROR,
                        returnCode = "MPI906",
                        returnMessage = "An error has occurred during init",
                    ),
                )
            }

            val enrollData = EnrollData.createInstance(
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
                ipAddress,
            )

            // ENROLL
            enroll(enrollData, accessToken) { enrollResult ->
                when (enrollResult.status) {
                    EnrollStatus.ENROLLED -> {
                        Log.i(TAG, "ENROLLED: $message, paReq: ${enrollResult.paReq}")
                        enrollSuccess(activity, enrollData, enrollResult)
                    }
                    EnrollStatus.VALIDATION_NEEDED -> {
                        if (enrollResult.transactionId == null) {
                            callback(
                                AuthenticationResponse(
                                    status = AuthenticationResponseStatus.ERROR,
                                    returnCode = "MSI667",
                                    returnMessage = "transactionId is null",
                                ),
                            )
                        } else {
                            validate(enrollData.toRequestValidate(enrollResult.transactionId), validateCallback)
                        }
                    }
                    EnrollStatus.AUTHENTICATION_CHECK_NEEDED -> {
                        if (enrollResult.authentication == null) {
                            callback(
                                AuthenticationResponse(
                                    status = AuthenticationResponseStatus.ERROR,
                                    returnCode = "MSI668",
                                    returnMessage = "authentication is null",
                                ),
                            )
                        } else {
                            checkAuthentication(enrollResult.authentication, callback)
                        }
                    }
                    EnrollStatus.NOT_ENROLLED -> {
                        callback(
                            AuthenticationResponse(
                                status = AuthenticationResponseStatus.UNENROLLED,
                                xId = enrollResult.xid,
                                eci = enrollResult.eci,
                                version = enrollResult.version,
                                referenceId = enrollResult.referenceId,
                                returnCode = enrollResult.authentication?.returnCode,
                                returnMessage = enrollResult.authentication?.returnMessage,
                            ),
                        )
                    }
                    EnrollStatus.FAILED -> {
                        callback(
                            AuthenticationResponse(
                                status = AuthenticationResponseStatus.FAILURE,
                                xId = enrollResult.xid,
                                eci = enrollResult.eci,
                                version = enrollResult.version,
                                referenceId = enrollResult.referenceId,
                                returnCode = enrollResult.authentication?.returnCode,
                                returnMessage = enrollResult.authentication?.returnMessage,
                            ),
                        )
                    }
                    EnrollStatus.UNSUPPORTED_BRAND -> {
                        callback(
                            AuthenticationResponse(
                                status = AuthenticationResponseStatus.UNSUPPORTED_BRAND,
                                returnCode = enrollResult.authentication?.returnCode,
                                returnMessage = enrollResult.authentication?.returnMessage,
                            ),
                        )
                    }
                    else -> {
                        callback(
                            AuthenticationResponse(
                                status = AuthenticationResponseStatus.ERROR,
                                returnCode = "MPI906",
                                returnMessage = "An error has occurred during enroll",
                            ),
                        )
                    }
                }
            }
        }
    }

    private fun initCardinal(
        activity: Context,
        order: RequestOrder,
        uiCustomization: UiCustomization,
        callback: (isSuccessful: Boolean, message: String) -> Unit,
    ) {
        braspagClient.getJwt(order, accessToken) {
            Log.i(TAG, ":::: getJwt response $it")

            val token = it.result?.token ?: String()

            if (token.isNotBlank()) {
                cardinal.cardinalInit(activity, token, uiCustomization) { isInitSuccessful, msg ->
                    callback(isInitSuccessful, msg)
                }
            } else {
                callback(false, it.errorMessage.toString())
            }
        }
    }

    private fun enroll(
        request: EnrollData,
        oauthToken: String,
        callback: (enrollResult: EnrollResult) -> Unit,
    ) {
        braspagClient.enrollData(request, oauthToken) { clientResult ->
            if (clientResult.result == null) {
                return@enrollData callback(
                    EnrollResult(
                        status = EnrollStatus.NOT_ENROLLED,
                        message = clientResult.errorMessage ?: "Not enrolled",
                    ),
                )
            }

            clientResult.result.also { response ->
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

                        if (eci == null) {
                            eci = response.authentication.eciRaw
                        }
                    }
                    else -> {
                        enrollStatus = EnrollStatus.ERROR
                    }
                }

                callback(
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
                        response.authentication.transactionId,
                    ),
                )
            }
        }
    }

    private fun validate(
        request: RequestValidate,
        callback: (isValidateSuccessful: Boolean, data: Authentication?) -> Unit,
    ) {
        braspagClient.validate(request, accessToken) {
            if (it.result?.status != null) {
                callback(true, it.result)
            } else {
                val errorResponse = Authentication(
                    returnCode = "MPI900",
                    returnMessage = "An error has occurred (${it.statusCode.code})",
                )

                callback(false, errorResponse)
            }
        }
    }

    private fun enrollSuccess(activity: Activity, enrollData: EnrollData, enrollResult: EnrollResult) {
        if (enrollData.authSuppressChallenge == true) {
            return callback(
                AuthenticationResponse(
                    status = AuthenticationResponseStatus.CHALLENGE_SUPPRESSION,
                    returnCode = "MPI601",
                    returnMessage = "Challenge suppressed",
                ),
            )
        }
        if (enrollResult.transactionId == null) {
            return callback(
                AuthenticationResponse(
                    status = AuthenticationResponseStatus.ERROR,
                    returnCode = "MSI667",
                    returnMessage = "transactionId is null",
                ),
            )
        }

        // CCACONTINUE
        ccaContinue(activity, enrollResult.transactionId, enrollResult.paReq ?: String()) {
            Log.i(TAG, ":::::::::::::: ccaContinue return: $it")

            when (it) {
                ActionCode.SUCCESS,
                ActionCode.NO_ACTION,
                ActionCode.FAILURE,
                ActionCode.ERROR,
                -> {
                    validate(enrollData.toRequestValidate(enrollResult.transactionId), validateCallback)
                }
                else -> {
                    callback(
                        AuthenticationResponse(
                            status = AuthenticationResponseStatus.ERROR,
                            returnCode = "MPI902",
                            returnMessage = "Unexpected authentication response",
                        ),
                    )
                }
            }
        }
    }

    private fun ccaContinue(
        currentActivity: Activity,
        transactionId: String,
        payload: String,
        callback: (ActionCode) -> Unit,
    ) {
        cardinal.cardinalCcaContinue(currentActivity, transactionId, payload) {
            callback(cardinal.convertToActionCode(it.actionCode))
        }
    }

    private fun checkAuthentication(
        authentication: Authentication,
        callback: (authResponse: AuthenticationResponse) -> Unit,
    ) {
        Log.i(TAG, "::::: checkAuthentication - authentication: $authentication")

        when (authentication.status) {
            AuthenticationStatus.AUTHENTICATED.name -> {
                callback(authentication.toResponse(AuthenticationResponseStatus.SUCCESS))
            }
            AuthenticationStatus.UNAVAILABLE.name -> {
                callback(authentication.toResponse(AuthenticationResponseStatus.UNENROLLED))
            }
            AuthenticationStatus.FAILED.name -> {
                callback(authentication.toResponse(AuthenticationResponseStatus.FAILURE))
            }
            AuthenticationStatus.ERROR_OCCURRED.name -> {
                callback(authentication.toResponse(AuthenticationResponseStatus.ERROR))
            }
            else -> {
                callback(authentication.toResponse(AuthenticationResponseStatus.ERROR))
            }
        }
    }

    private val validateCallback: (isSuccessful: Boolean, data: Authentication?) -> Unit = { isSuccessful, data ->
        Log.i(TAG, "------ validateCallback - $isSuccessful - return: $data")

        try {
            if (data != null) {
                checkAuthentication(data, callback)
            } else {
                callback.invoke(
                    AuthenticationResponse(
                        status = AuthenticationResponseStatus.ERROR,
                        returnCode = "MSI666",
                        returnMessage = "authentication is null",
                    ),
                )
            }
        } catch (t: Throwable) {
            Log.e(TAG, "::::: ERROR on validateCallback - $t")
        }

        cardinal.cardinalCleanupInstance()
    }

    private fun setupAppCenterTrack(activity: Activity, JWTEncoded: String, environment: Environment) {
        AppCenter.start(
            activity.application,
            BuildConfig.APP_CENTER_KEY,
            Analytics::class.java,
            Crashes::class.java,
        )

        val jwt = BraspagJwt(JWTEncoded)

        val properties = HashMap<String, String>().apply {
            this["clientId"] = jwt.clientId
            this["clientName"] = jwt.clientName
        }

        when (environment) {
            Environment.SANDBOX -> {
                Analytics.trackEvent("SANDBOX", properties)
            }
            Environment.PRODUCTION -> {
                Analytics.trackEvent("PRODUCTION", properties)
            }
        }
    }

    private fun Authentication.toResponse(status: AuthenticationResponseStatus): AuthenticationResponse {
        return AuthenticationResponse(
            status,
            cavv = this.cavv.takeIf { status == AuthenticationResponseStatus.SUCCESS },
            xId = this.xId,
            eci = this.eci ?: this.eciRaw,
            version = this.version,
            referenceId = this.transactionId.takeIf { status != AuthenticationResponseStatus.ERROR },
            returnCode = this.returnCode,
            returnMessage = this.returnMessage,
        )
    }
}
