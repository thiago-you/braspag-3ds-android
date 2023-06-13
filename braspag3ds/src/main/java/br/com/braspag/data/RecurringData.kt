package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecurringData(
    @SerializedName("endDate")
    val endDate: String? = null,
    @SerializedName("frequency")
    val frequency: RecurringFrequency? = null,
    @SerializedName("originalPurchaseDate")
    val originalPurchaseDate: String? = null,
) : Serializable
