package br.com.braspag.data

import com.google.gson.annotations.SerializedName

enum class RecurringFrequency {
    @SerializedName("1")
    MONTHLY,

    @SerializedName("2")
    BIMONTHLY,

    @SerializedName("3")
    TRIMONTHLY,

    @SerializedName("4")
    TRIANNUAL,

    @SerializedName("6")
    SEMIANNUAL,

    @SerializedName("12")
    YEARLY
}