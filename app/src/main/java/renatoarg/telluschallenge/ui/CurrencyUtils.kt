package renatoarg.telluschallenge.ui

import java.text.NumberFormat
import java.util.Currency

fun Long.toUsdCurrency(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("USD")
    return format.format(this)
}