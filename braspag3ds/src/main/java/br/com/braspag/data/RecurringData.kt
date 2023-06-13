package br.com.braspag.data

data class RecurringData(
    val endDate: String? = null,
    val frequency: RecurringFrequency? = null,
    val originalPurchaseDate: String? = null,
)
