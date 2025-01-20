package com.example.healthlylife.functions

import android.util.Log
import com.example.healthlylife.data.UserFormData
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun calculateAge(birthDate: String, userFormData: UserFormData) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val birthDateLocal = LocalDate.parse(birthDate, formatter)
    val currentDate = LocalDate.now()
    val period = Period.between(birthDateLocal, currentDate)

    userFormData.age = period.years
    // Logowanie wyników

}
