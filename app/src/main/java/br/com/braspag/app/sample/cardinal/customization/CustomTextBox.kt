package br.com.braspag.app.sample.cardinal.customization

import com.cardinalcommerce.shared.userinterfaces.TextBoxCustomization

class CustomTextBox(
    borderColor: String,
    borderWidth: Int,
    cornerRadius: Int,
    textColor: String,
    textFontName: String,
    textFontSize: Int,
) : TextBoxCustomization() {
    init {
        this.borderColor = borderColor
        this.borderWidth = borderWidth
        this.cornerRadius = cornerRadius
        this.textColor = textColor
        this.textFontName = textFontName
        this.textFontSize = textFontSize
    }
}
