package br.com.braspag.internal.extensions

internal fun String.beared(): String {
    return "Bearer $this"
}
