package br.com.braspag.internal.cardinal

import android.app.Activity
import android.content.Context
import android.util.Log
import br.com.braspag.data.Environment
import br.com.braspag.internal.data.ActionCode
import com.cardinalcommerce.cardinalmobilesdk.Cardinal
import com.cardinalcommerce.cardinalmobilesdk.enums.CardinalEnvironment
import com.cardinalcommerce.cardinalmobilesdk.enums.CardinalRenderType
import com.cardinalcommerce.cardinalmobilesdk.enums.CardinalUiType
import com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode
import com.cardinalcommerce.cardinalmobilesdk.models.CardinalConfigurationParameters
import com.cardinalcommerce.cardinalmobilesdk.models.ValidateResponse
import com.cardinalcommerce.cardinalmobilesdk.services.CardinalInitService
import com.cardinalcommerce.shared.userinterfaces.UiCustomization
import org.json.JSONArray

internal open class CardinalHelper(private val environment: Environment) {

    companion object {
        const val TAG = "CardinalHelper"
    }

    private lateinit var cardinal: Cardinal

    fun cardinalInit(
        context: Context,
        jwt: String,
        uiCustomization: UiCustomization,
        callback: (Boolean, String) -> Unit,
    ) {
        try {
            cardinal = Cardinal.getInstance()
            cardinalConfigure(context, uiCustomization)

            cardinal.init(
                jwt,
                object : CardinalInitService {
                    override fun onSetupCompleted(consumerSessionId: String) {
                        Log.i(TAG, ":::: onSetupCompleted == $consumerSessionId")
                        callback.invoke(true, consumerSessionId)
                    }

                    override fun onValidated(validateResponse: ValidateResponse, jwt: String?) {
                        Log.i(TAG, ":::: onValidated == $validateResponse")
                        callback.invoke(false, validateResponse.errorNumber.toString())
                    }
                },
            )
        } catch (e: Throwable) {
            Log.e(TAG, "Error on cardinalInit: $e")

            if (e.localizedMessage != null) {
                callback.invoke(false, e.localizedMessage!!)
            } else {
                callback.invoke(false, "Unknown error")
            }
        }
    }

    private fun cardinalConfigure(context: Context, uiCustomization: UiCustomization) {
        val configParams = CardinalConfigurationParameters()

        configParams.environment =
            if (environment == Environment.SANDBOX) CardinalEnvironment.STAGING else CardinalEnvironment.PRODUCTION
        configParams.requestTimeout = 8000
        configParams.challengeTimeout = 5

        val renderType = JSONArray()

        with(renderType) {
            put(CardinalRenderType.OTP)
            put(CardinalRenderType.SINGLE_SELECT)
            put(CardinalRenderType.MULTI_SELECT)
            put(CardinalRenderType.OOB)
            put(CardinalRenderType.HTML)
        }

        configParams.renderType = renderType
        configParams.uiType = CardinalUiType.BOTH
        configParams.uiCustomization = uiCustomization

        cardinal.configure(context, configParams)
    }

    fun cardinalCcaContinue(
        currentActivity: Activity,
        transactionId: String,
        payload: String,
        callback: (ValidateResponse) -> Unit,
    ) {
        cardinal.cca_continue(transactionId, payload, currentActivity) { _, validateResponse, _ ->
            callback.invoke(validateResponse)
        }
    }

    fun convertToActionCode(cardinalActionCode: CardinalActionCode): ActionCode {
        return when (cardinalActionCode) {
            CardinalActionCode.ERROR -> ActionCode.ERROR
            CardinalActionCode.SUCCESS -> ActionCode.SUCCESS
            CardinalActionCode.CANCEL -> ActionCode.CANCEL
            CardinalActionCode.FAILURE -> ActionCode.FAILURE
            CardinalActionCode.NOACTION -> ActionCode.NO_ACTION
            CardinalActionCode.TIMEOUT -> ActionCode.TIMEOUT
        }
    }

    fun cardinalCleanupInstance() = Cardinal.getInstance().cleanup()
}
