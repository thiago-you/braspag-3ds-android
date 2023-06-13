package br.com.braspag.customization

import com.cardinalcommerce.shared.models.enums.ButtonType as CardinalButtonType

enum class ButtonType(val value: CardinalButtonType) {
    VERIFY(CardinalButtonType.VERIFY),
    CONTINUE(CardinalButtonType.CONTINUE),
    NEXT(CardinalButtonType.NEXT),
    CANCEL(CardinalButtonType.CANCEL),
    RESEND(CardinalButtonType.RESEND),
}
