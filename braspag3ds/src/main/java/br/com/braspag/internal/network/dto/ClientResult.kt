package br.com.braspag.internal.network.dto

import br.com.braspag.internal.extensions.HttpStatusCode
import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class ClientResult<T>(
    @SerializedName("result")
    val result: T?,
    @SerializedName("statusCode")
    val statusCode: HttpStatusCode,
    @SerializedName("errorMessage")
    val errorMessage: String? = null,
) : Serializable
