package wevs.com.br.transferapp.utils

import wevs.com.br.transferapp.R
import java.math.BigDecimal
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun BigDecimal.formatToBrazilianCurrency(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat.format(this)
}

fun BigDecimal.returnsColors(): Int =
    if (this >= BigDecimal.ZERO) R.color.positive_color else R.color.negative_color

fun String.formatBrazilianDate(inputFormat: String, returnFormat: String): String {
    val formatter: DateFormat = SimpleDateFormat(inputFormat, Locale.US)
    val date = formatter.parse(this) as Date
    val newFormat = SimpleDateFormat(returnFormat, Locale.getDefault())
    return newFormat.format(date)
}