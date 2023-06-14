package br.com.braspag.app.sample.cardinal.customization

import com.cardinalcommerce.shared.models.enums.ButtonType as CardinalButtonType

enum class ButtonType(val value: CardinalButtonType) {
    VERIFY(CardinalButtonType.VERIFY),
    CONTINUE(CardinalButtonType.CONTINUE),
    NEXT(CardinalButtonType.NEXT),
    CANCEL(CardinalButtonType.CANCEL),
    RESEND(CardinalButtonType.RESEND),
}
