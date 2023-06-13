package br.com.braspag.internal.extensions

import br.com.braspag.data.OrderData
import br.com.braspag.internal.network.dto.RequestOrder

internal fun OrderData.toRequestOrder() = RequestOrder(
    orderNumber = this.orderNumber,
    currency = this.currencyCode,
    amount = this.totalAmount,
)
