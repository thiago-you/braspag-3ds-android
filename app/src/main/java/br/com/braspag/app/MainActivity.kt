package br.com.braspag.app

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import br.com.braspag.Braspag3ds
import br.com.braspag.customization.*
import br.com.braspag.data.*

// RESULTS
const val SUCCESS_STATUS = "SUCCESS"
const val FAILURE_STATUS = "FAILURE"
const val UNENROLLED_STATUS = "UNENROLLED"
const val UNSUPPORTED_BRAND_STATUS = "UNSUPPORTED_BRAND"
const val CHALLENGE_SUPPRESSION_STATUS = "CHALLENGE_SUPPRESSED"
const val ERROR_STATUS = "ERROR"

class MainActivity : Activity() {

    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // INSTANTIATE
        val braspag3ds = Braspag3ds(Environment.SANDBOX)

        braspag3ds.customize(
            toolbarCustomization = CustomToolbar(
                backgroundColor = "#00c1eb",
                buttonText = "Cancel",
                headerText = "BRASPAG 3DS",
                textColor = "#ffffff",
                textFontName = "font/amaticsc.ttf",
                textFontSize = 16
            ),
            textBoxCustomization = CustomTextBox(
                borderColor = "#1f567d",
                borderWidth = 10,
                cornerRadius = 25,
                textColor = "#000000",
                textFontName = "font/amaticsc.ttf",
                textFontSize = 24
            ),
            labelCustomization = CustomLabel(
                headingTextColor = "#404040",
                headingTextFontName = "font/amaticsc.ttf",
                headingTextFontSize = 24,
                textColor = "#404040",
                textFontName = "font/amaticsc.ttf",
                textFontSize = 16
            ),
            buttons = listOf(
                CustomButton(
                    textColor = "#ffffff",
                    backgroundColor = "#5ea9d1",
                    textFontName = "font/amaticsc.ttf",
                    cornerRadius = 25,
                    textFontSize = 16,
                    type = ButtonType.VERIFY
                ),
                CustomButton(
                    textColor = "#ffffff",
                    backgroundColor = "#5ea9d1",
                    textFontName = "font/amaticsc.ttf",
                    cornerRadius = 25,
                    textFontSize = 16,
                    type = ButtonType.CONTINUE
                ),
                CustomButton(
                    textColor = "#ffffff",
                    backgroundColor = "#5ea9d1",
                    textFontName = "font/amaticsc.ttf",
                    cornerRadius = 25,
                    textFontSize = 16,
                    type = ButtonType.NEXT
                ),
                CustomButton(
                    textColor = "#5ea9d1",
                    backgroundColor = "#ffffff",
                    textFontName = "font/amaticsc.ttf",
                    cornerRadius = 25,
                    textFontSize = 16,
                    type = ButtonType.RESEND
                ),
                CustomButton(
                    textColor = "#ff0000",
                    backgroundColor = "#00c1eb",
                    textFontName = "font/amaticsc.ttf",
                    cornerRadius = 25,
                    textFontSize = 20,
                    type = ButtonType.CANCEL
                )
            )
        )

        // Avoiding NetworkOnMainThreadException
        ioScope.launch {
            // AUTHENTICATION
            braspag3ds.authenticate(
                "ACCESS-TOKEN",
                orderData = OrderData(
                    orderNumber = "123456",
                    currencyCode = "986",
                    totalAmount = 1000L,
                    paymentMethod = PaymentMethod.credit,
                    transactionMode = TransactionMode.eCommerce,
                    installments = 3,
                    merchantUrl = "https://www.exemplo.com.br"
                ),
                cardData = CardData(
                    number = "4000000000001091",
                    expirationMonth = "01",
                    expirationYear = "2023"
                ),
                authOptions = OptionsData(
                    notifyOnly = false,
                    suppressChallenge = false
                ),
                shipToData = ShipToData(
                    sameAsBillTo = true,
                    addressee = "Rua do Meio, 123",
                    city = "Praia Grande",
                    country = "BR",
                    email = "maurici@email.com",
                    state = "SP",
                    shippingMethod = "lowcost",
                    zipCode = "11726-000"
                ),
                recurringData = RecurringData(
                    frequency = RecurringFrequency.MONTHLY
                ),
                userData = UserData(
                    newCustomer = false,
                    authenticationMethod = AuthenticationMethod.noAuthentication
                ),
                activity = this@MainActivity,
                callback = callback
            )
        }
    }

    private val callback: (AuthenticationResponse) -> Unit = { authenticationResponse ->
        when (authenticationResponse.status) {
            AuthenticationResponseStatus.SUCCESS -> {
                showResults(
                    authenticationResponse.toAuthenticationResult(
                        SUCCESS_STATUS
                    )
                )
            }

            AuthenticationResponseStatus.FAILURE -> {
                showResults(
                    authenticationResponse.toAuthenticationResult(
                        FAILURE_STATUS
                    )
                )
            }

            AuthenticationResponseStatus.UNENROLLED -> {
                showResults(
                    authenticationResponse.toAuthenticationResult(
                        UNENROLLED_STATUS
                    )
                )
            }

            AuthenticationResponseStatus.UNSUPPORTED_BRAND -> {
                showResults(
                    authenticationResponse.toAuthenticationResult(
                        UNSUPPORTED_BRAND_STATUS
                    )
                )
            }

            AuthenticationResponseStatus.CHALLENGE_SUPPRESSION -> {
                showResults(
                    authenticationResponse.toAuthenticationResult(
                        CHALLENGE_SUPPRESSION_STATUS
                    )
                )
            }

            AuthenticationResponseStatus.ERROR -> {
                showResults(
                    authenticationResponse.toAuthenticationResult(
                        ERROR_STATUS
                    )
                )
            }
        }
    }

    private fun showResults(authenticationResult: AuthenticationResult) {
        // Avoiding CalledFromWrongThreadException
        runOnUiThread {
            result.text = authenticationResult.toStringResults()
        }
    }

    private fun AuthenticationResponse.toAuthenticationResult(result: String? = null) =
        AuthenticationResult(
            status = this.status,

            result = result ?: this.returnCode,
            message = this.returnMessage,

            returnCode = this.returnCode,
            returnMessage = this.returnMessage,
            cavv = this.cavv,
            eci = this.eci,
            xId = this.xId,
            version = this.version,
            referenceId = this.referenceId
        )

    private fun AuthenticationResult.toStringResults(): String {
        when (this.status) {

            AuthenticationResponseStatus.UNSUPPORTED_BRAND -> {
                result = UNSUPPORTED_BRAND_STATUS
                message = getString(R.string.braspag_message_unsupported_brand)
            }

            AuthenticationResponseStatus.CHALLENGE_SUPPRESSION -> {
                result = CHALLENGE_SUPPRESSION_STATUS
                message = getString(R.string.braspag_message_challenge_suppressed)
            }

            AuthenticationResponseStatus.SUCCESS -> {
                result = SUCCESS_STATUS
                message = getString(R.string.braspag_message_success)
            }

            AuthenticationResponseStatus.UNENROLLED -> {
                result = UNENROLLED_STATUS
                message = getString(R.string.braspag_message_unenrolled)
            }

            AuthenticationResponseStatus.FAILURE -> {
                result = FAILURE_STATUS
                message = getString(R.string.braspag_message_failure)
            }

            AuthenticationResponseStatus.ERROR -> {
                result = ERROR_STATUS
//                message = getString(R.string.braspag_message_error)
            }
        }

        return "Result: $result\n" +
                "Message: $message\n" +
                "CAVV: $cavv\n" +
                "ECI: $eci\n" +
                "XID: $xId\n" +
                "Version: $version\n" +
                "ReferenceId: $referenceId\n" +
                "Error Code: $errorCode\n" +
                "Error Message: $errorMessage"
    }
}

data class AuthenticationResult(
    val status: AuthenticationResponseStatus,

    var result: String? = null,
    var message: String? = null,

    val returnCode: String?,
    val returnMessage: String?,

    val cavv: String?,
    val eci: String?,
    val xId: String?,

    val version: String?,
    val referenceId: String? = null,

    val errorCode: String? = null,
    val errorMessage: String? = null
)