package br.com.braspag.internal.cardinal

import android.app.Activity
import android.content.Context
import br.com.braspag.internal.data.ActionCode

interface CardinalReference {
    fun cardinalInit(context: Context, jwt: String, callback: (Boolean, String) -> Unit)
    fun cardinalCcaContinue(currentActivity: Activity, transactionId: String, payload: String, callback: (Any) -> Unit)
    fun convertToActionCode(cardinalActionCode: Any): ActionCode
    fun cardinalCleanupInstance()
}
