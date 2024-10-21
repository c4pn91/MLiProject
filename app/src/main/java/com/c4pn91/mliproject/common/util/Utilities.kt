package com.c4pn91.mliproject.common.util

import java.text.DecimalFormat
import java.text.NumberFormat

object Utilities {
    fun formatFloatToCurrency(value: Double?): String {
        if (value == null) return ""
        val formatter = NumberFormat.getCurrencyInstance() as DecimalFormat
        val symbols = formatter.decimalFormatSymbols

        // Se establece el símbolo de moneda con espacio adicional
        symbols.currencySymbol = "${symbols.currencySymbol} "
        formatter.decimalFormatSymbols = symbols

        // Omitimos los decimales
        formatter.maximumFractionDigits = 0

        // Se formatea el valor y retornar
        return formatter.format(value)
    }
}