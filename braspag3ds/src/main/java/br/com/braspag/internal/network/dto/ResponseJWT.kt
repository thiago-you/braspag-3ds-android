package br.com.braspag.internal.network.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class ResponseJWT(
    @SerializedName("ReferenceId")
    val referenceId: String,
    @SerializedName("Token")
    val token: String,
) : Serializable
