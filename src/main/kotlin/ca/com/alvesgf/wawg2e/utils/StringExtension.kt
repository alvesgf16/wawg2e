package ca.com.alvesgf.wawg2e.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.turnIntoAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var age: Int
    val birthDate = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    age = Period.between(birthDate, today).years

    return age
}