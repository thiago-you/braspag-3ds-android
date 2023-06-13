package br.com.braspag.internal.data

import br.com.braspag.internal.network.dto.Authentication

internal data class EnrollResult (
    val status: EnrollStatus,
    val authentication: Authentication? = null,
    val message: String,
    val transactionId: String? = null,
    val paReq: String? = null,
    val xid: String? = null,
    val eci: String? = null,
    val cavv: String? = null,
    val version: String? = null,
    val referenceId: String? = null,
)
