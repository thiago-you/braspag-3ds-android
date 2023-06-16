package br.com.braspag.internal.extensions

import br.com.braspag.internal.data.EnrollData
import br.com.braspag.internal.network.dto.RequestValidate

internal fun EnrollData.toRequestValidate(transactionId: String) = RequestValidate(
    orderNumber = this.orderNumber,
    currency = this.currency,
    totalAmount = this.totalAmount,
    paymentMethod = this.paymentMethod,
    cardNumber = this.cardNumber,
    cardExpirationYear = this.cardExpirationYear,
    cardExpirationMonth = this.cardExpirationMonth,
    authNotifyOnly = this.authNotifyOnly,
    authSuppressChallenge = this.authSuppressChallenge,
    transactionId = transactionId,
)
