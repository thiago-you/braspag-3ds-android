package br.com.braspag.internal.components

import android.util.Base64
import org.json.JSONObject

class BraspagJwt(private val token: String?) {

    var clientId: String = String()
        private set

    var clientName: String = String()
        private set

    companion object {
        private const val JWT_DATA_INDEX = 1
        private const val KEY_CLIENT_ID = "client_id"
        private const val KEY_CLIENT_NAME = "client_name"
    }

    init {
        setupTokenInfo()
    }

    private fun setupTokenInfo() {
        if (token.isNullOrBlank()) {
            return
        }

        runCatching {
            val data = token?.split(".") ?: return

            if (JWT_DATA_INDEX in data.indices) {
                val json = getJsonObject(data[JWT_DATA_INDEX]) ?: return

                if (json.has(KEY_CLIENT_ID)) {
                    clientId = json.getString(KEY_CLIENT_ID)
                }
                if (json.has(KEY_CLIENT_NAME)) {
                    clientName = json.getString(KEY_CLIENT_NAME)
                }
            }
        }
    }

    private fun getJsonObject(value: String?): JSONObject? {
        if (value.isNullOrBlank()) {
            return null
        }

        runCatching { JSONObject(Base64.decode(value, Base64.DEFAULT).decodeToString()) }.onSuccess {
            return it
        }

        return null
    }
}
