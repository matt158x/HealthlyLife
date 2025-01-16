package com.example.healthlylife.functions

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

// Funkcja do obliczania wieku
fun calculateAge(birthDate: String): Int {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val birthDateLocal = LocalDate.parse(birthDate, formatter)
    val currentDate = LocalDate.now()
    val period = Period.between(birthDateLocal, currentDate)
    return period.years
}