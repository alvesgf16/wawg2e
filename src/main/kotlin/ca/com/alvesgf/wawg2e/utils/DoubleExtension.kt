package ca.com.alvesgf.wawg2e.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.formatWithTwoDecimalPlaces(): Double {
    val decimalFormat = DecimalFormat("#.00", DecimalFormatSymbols(Locale.CANADA))
    return decimalFormat.format(this).toDouble()
}