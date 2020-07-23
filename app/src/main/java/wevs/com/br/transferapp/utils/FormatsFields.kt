package wevs.com.br.transferapp.utils

import wevs.com.br.transferapp.R
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatToBrazilianCurrency(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat.format(this)
}

fun BigDecimal.returnsColors(): Int =
    if (this >= BigDecimal.ZERO) R.color.positive_color else R.color.negative_color

fun String.formatBrazilianDate(): String {
    return this
}