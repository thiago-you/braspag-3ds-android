package br.com.braspag.data

data class CardData(
    val number: String,
    val expirationMonth: String,
    val expirationYear: String,
    val cardAlias: String? = null,
    val defaultCard: Boolean? = null,
)
