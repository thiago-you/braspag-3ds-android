package br.com.braspag.data

enum class ProductCode(val value: String) {
    hotel("ACC"),
    financeAccount("ACF"),
    checkAcceptance("CHA"),
    digitalGoods("DIG"),
    cashDispenser("DSP"),
    fuel("GAS"),
    retail("GEN"),
    luxuryGoods("LUX"),
    recharge("PAL"),
    goodsPurchase("PHY"),
    quasiMoneyTransaction("QCT"),
    carRental("REN"),
    restaurant("RES"),
    services("SVC"),
    other("TBD"),
    turism("TRA")
}