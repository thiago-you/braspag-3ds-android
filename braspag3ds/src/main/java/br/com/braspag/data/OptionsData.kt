package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OptionsData(
    @SerializedName("notifyOnly")
    val notifyOnly: Boolean = false,
    @SerializedName("suppressChallenge")
    val suppressChallenge: Boolean = false,
) : Serializable
