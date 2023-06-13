package br.com.braspag.internal.data

import br.com.braspag.internal.network.dto.Authentication
import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class EnrollResult(
    @SerializedName("status")
    val status: EnrollStatus,
    @SerializedName("authentication")
    val authentication: Authentication? = null,
    @SerializedName("message")
    val message: String,
    @SerializedName("transactionId")
    val transactionId: String? = null,
    @SerializedName("paReq")
    val paReq: String? = null,
    @SerializedName("xid")
    val xid: String? = null,
    @SerializedName("eci")
    val eci: String? = null,
    @SerializedName("cavv")
    val cavv: String? = null,
    @SerializedName("version")
    val version: String? = null,
    @SerializedName("referenceId")
    val referenceId: String? = null,
) : Serializable
