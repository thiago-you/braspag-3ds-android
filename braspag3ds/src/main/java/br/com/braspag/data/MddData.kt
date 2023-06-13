package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MddData(
    @SerializedName("mdd1")
    val mdd1: String? = null,
    @SerializedName("mdd2")
    val mdd2: String? = null,
    @SerializedName("mdd3")
    val mdd3: String? = null,
    @SerializedName("mdd4")
    val mdd4: String? = null,
    @SerializedName("mdd5")
    val mdd5: String? = null,
) : Serializable
