package br.com.braspag.internal.network

import br.com.braspag.app.BuildConfig
import br.com.braspag.data.Environment
import br.com.braspag.internal.data.EnrollData
import br.com.braspag.internal.extensions.HttpStatusCode
import br.com.braspag.internal.extensions.beared
import br.com.braspag.internal.extensions.toStatusCode
import br.com.braspag.internal.network.dto.Authentication
import br.com.braspag.internal.network.dto.ClientResult
import br.com.braspag.internal.network.dto.RequestOrder
import br.com.braspag.internal.network.dto.RequestValidate
import br.com.braspag.internal.network.dto.ResponseEnroll
import br.com.braspag.internal.network.dto.ResponseJWT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class BraspagClient(environment: Environment = Environment.SANDBOX) {

    companion object {
        private const val SANDBOX_URL = "https://mpisandbox.braspag.com.br/"
        private const val PRODUCTION_URL = "https://mpi.braspag.com.br/"
    }

    private val service = WebClient(getEnvironmentUrl(environment)).createService(BraspagApi::class.java)

    fun getJwt(
        request: RequestOrder,
        oauthToken: String,
        callback: (model: ClientResult<ResponseJWT>) -> Unit,
    ) {
        val call = service.getJWT(oauthToken.beared(), request)

        call.enqueue(object : Callback<ResponseJWT> {
            override fun onFailure(call: Call<ResponseJWT>, t: Throwable) {
                callback(resultFailure(t))
            }

            override fun onResponse(call: Call<ResponseJWT>, response: Response<ResponseJWT>) {
                if (!response.isSuccessful) {
                    return callback(resultError(response))
                }

                if (response.body() != null) {
                    callback(resultSuccess(response))
                } else {
                    val message = "The response object is null for getJwt. Error %s - %s".let {
                        String.format(it, response.code(), response.code().toStatusCode())
                    }

                    callback(resultInvalid(response, message))
                }
            }
        })
    }

    fun enrollData(
        enrollData: EnrollData,
        oauthToken: String,
        callback: (model: ClientResult<ResponseEnroll>) -> Unit,
    ) {
        val xSdkVersion = BuildConfig.X_SDK_VERSION
        val call = service.enroll(oauthToken.beared(), xSdkVersion, enrollData)

        call.enqueue(object : Callback<ResponseEnroll> {
            override fun onFailure(call: Call<ResponseEnroll>, t: Throwable) {
                callback.invoke(resultFailure(t))
            }

            override fun onResponse(call: Call<ResponseEnroll>, response: Response<ResponseEnroll>) {
                if (!response.isSuccessful) {
                    return callback(resultError(response))
                }

                if (response.body() != null) {
                    callback(resultSuccess(response))
                } else {
                    callback(resultInvalid(response, "The response object is null for enroll."))
                }
            }
        })
    }

    fun validate(
        request: RequestValidate,
        oauthToken: String,
        callback: (model: ClientResult<Authentication>) -> Unit,
    ) {
        val call = service.validate(oauthToken.beared(), request)

        call.enqueue(object : Callback<Authentication> {
            override fun onFailure(call: Call<Authentication>, t: Throwable) {
                callback.invoke(resultFailure(t))
            }

            override fun onResponse(call: Call<Authentication>, response: Response<Authentication>) {
                if (!response.isSuccessful) {
                    return callback(resultError(response))
                }

                if (response.body() != null) {
                    callback(resultSuccess(response))
                } else {
                    callback(resultInvalid(response, "The response object is null for validate."))
                }
            }
        })
    }

    private fun getEnvironmentUrl(environment: Environment): String {
        return if (environment == Environment.SANDBOX) {
            SANDBOX_URL
        } else {
            PRODUCTION_URL
        }
    }

    private fun <T> resultSuccess(response: Response<T>): ClientResult<T> {
        return ClientResult(response.body(), response.code().toStatusCode())
    }

    private fun <T> resultInvalid(response: Response<T>, message: String): ClientResult<T> {
        return ClientResult(null, response.code().toStatusCode(), message)
    }

    private fun <T> resultError(response: Response<T>): ClientResult<T> {
        return ClientResult(
            null,
            response.code().toStatusCode(),
            "Error ${response.code()} - ${response.code().toStatusCode()}",
        )
    }

    private fun <T> resultFailure(throwable: Throwable): ClientResult<T> {
        return ClientResult(
            null,
            HttpStatusCode.Unknown,
            throwable.localizedMessage ?: "Unknown Error",
        )
    }
}
