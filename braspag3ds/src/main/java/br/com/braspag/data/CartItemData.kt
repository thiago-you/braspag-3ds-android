package br.com.braspag.data

data class CartItemData(
    val name: String,
    val description: String? = null,
    val sku: String? = null,
    val quantity: Long? = null,
    val unitPrice: Long? = null,
)
