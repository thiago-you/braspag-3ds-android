package br.com.braspag.customization

import com.cardinalcommerce.shared.userinterfaces.ButtonCustomization

class CustomButton(
    textColor: String,
    backgroundColor: String,
    textFontName: String,
    cornerRadius: Int,
    textFontSize: Int,
    val type: ButtonType
) : ButtonCustomization () {
    init {
        this.textColor = textColor
        this.backgroundColor = backgroundColor
        this.textFontName = textFontName
        this.cornerRadius = cornerRadius
        this.textFontSize = textFontSize
    }
}