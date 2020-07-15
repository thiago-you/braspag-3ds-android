package br.com.braspag.data

enum class TransactionMode(val value: String) {
    moto("M"),
    retail("R"),
    eCommerce("S"),
    mobile("P"),
    tablet("T")
}