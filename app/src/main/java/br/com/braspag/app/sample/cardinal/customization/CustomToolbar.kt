package br.com.braspag.app.sample.cardinal.customization

import com.cardinalcommerce.shared.userinterfaces.ToolbarCustomization

class CustomToolbar(
    backgroundColor: String,
    buttonText: String,
    headerText: String,
    textColor: String,
    textFontName: String,
    textFontSize: Int,
) : ToolbarCustomization() {
    init {
        this.backgroundColor = backgroundColor
        this.buttonText = buttonText
        this.headerText = headerText
        this.textColor = textColor
        this.textFontName = textFontName
        this.textFontSize = textFontSize
    }
}
