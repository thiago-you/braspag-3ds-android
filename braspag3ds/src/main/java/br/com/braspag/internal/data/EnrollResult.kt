package br.com.braspag.internal.data

import br.com.braspag.internal.network.dto.Authentication

internal data class EnrollResult (
    val status: EnrollStatus,
    val authentication: Authentication?,
    val message: String,
    val transactionId: String?,
    val paReq: String?,
    val xid: String?,
    val eci: String?,
    val cavv: String?,
    val version: String?,
    val referenceId: String?
)
