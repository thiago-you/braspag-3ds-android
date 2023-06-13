package br.com.braspag.customization

import com.cardinalcommerce.shared.userinterfaces.LabelCustomization

class CustomLabel(
    headingTextColor: String,
    headingTextFontName: String,
    headingTextFontSize: Int,
    textColor: String,
    textFontName: String,
    textFontSize: Int,
) : LabelCustomization() {
    init {
        this.headingTextColor = headingTextColor
        this.headingTextFontName = headingTextFontName
        this.headingTextFontSize = headingTextFontSize
        this.textColor = textColor
        this.textFontName = textFontName
        this.textFontSize = textFontSize
    }
}
