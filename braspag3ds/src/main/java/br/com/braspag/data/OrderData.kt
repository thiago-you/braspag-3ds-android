package br.com.braspag.data

data class OrderData(
    val orderNumber: String,
    val currencyCode: String = "BRL",
    val totalAmount: Long,
    val paymentMethod: PaymentMethod,
    val installments: Int? = null,
    val recurrence: Boolean? = null,
    val productCode: ProductCode? = null,
    val countLast24Hours: Int? = null,
    val countLast6Months: Int? = null,
    val countLast1Year: Int? = null,
    val cardAttemptsLast24Hours: Int? = null,
    val marketingOptIn: Boolean? = null,
    val marketingSource: String? = null,
    val transactionMode: TransactionMode? = null,
    val merchantUrl: String? = null,
)
