package br.com.braspag.data

import com.google.gson.annotations.SerializedName

enum class ProductCode {
    @SerializedName("ACC")
    hotel,

    @SerializedName("ACF")
    financeAccount,

    @SerializedName("CHA")
    checkAcceptance,

    @SerializedName("DIG")
    digitalGoods,

    @SerializedName("DSP")
    cashDispenser,

    @SerializedName("GAS")
    fuel,

    @SerializedName("GEN")
    retail,

    @SerializedName("LUX")
    luxuryGoods,

    @SerializedName("PAL")
    recharge,

    @SerializedName("PHY")
    goodsPurchase,

    @SerializedName("QCT")
    quasiMoneyTransaction,

    @SerializedName("REN")
    carRental,

    @SerializedName("RES")
    restaurant,

    @SerializedName("SVC")
    services,

    @SerializedName("TBD")
    other,

    @SerializedName("TRA")
    turism
}