package br.com.braspag.data

enum class ProductCode(val value: String) {
    HOTEL("ACC"),
    FINANCE_ACCOUNT("ACF"),
    CHECK_ACCEPTANCE("CHA"),
    DIGITAL_GOODS("DIG"),
    CASH_DISPENSER("DSP"),
    FUEL("GAS"),
    RETAIL("GEN"),
    LUXURY_GOODS("LUX"),
    RECHARGE("PAL"),
    GOODS_PURCHASE("PHY"),
    QUASI_MONEY_TRANSACTION("QCT"),
    CAR_RENTAL("REN"),
    RESTAURANT("RES"),
    SERVICES("SVC"),
    OTHER("TBD"),
    TOURISM("TRA"),
}
