package br.com.braspag.internal.network.dto

import br.com.braspag.internal.extensions.HttpStatusCode

internal data class ClientResult<T>(
    val result: T?,
    val statusCode: HttpStatusCode,
    val errorMessage: String? = null
)