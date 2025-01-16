package com.example.healthlylife.functions

import com.example.healthlylife.data.UserFormData
import java.text.DateFormatSymbols
import java.util.Calendar


fun calculateCalorieIntake(userFormData: UserFormData): Double {
    val weight = userFormData.currentWeight  // konwersja na Double
    val height = userFormData.height  // konwersja na Double
    val age = userFormData.age.toDouble()  // konwersja na Double

    val bmr: Double
    if (userFormData.gender == "Male") {
        bmr = 10 * userFormData.currentWeight + 6.25 * userFormData.height - 5 * userFormData.age + 5
    } else {
        bmr = 10 * userFormData.currentWeight + 6.25 * userFormData.height - 5 * userFormData.age - 161
    }

    return when (userFormData.goal) {
        "GAIN WEIGHT" -> bmr + 500  // Dodatkowe 500 kcal dziennie
        "LOSE WEIGHT" -> bmr - 500  // Mniej 500 kcal dziennie
        else -> bmr  // Utrzymanie wagi
    }
}

fun calculateGoalCompletionDate(userFormData: UserFormData): String {
    val goalWeight = userFormData.targetWeight
    val weeklyChange = if (userFormData.goal == "GAIN WEIGHT") 0.5 else -0.5
    val daysToGoalWeight = Math.ceil((goalWeight - userFormData.currentWeight) / (weeklyChange / 7)).toInt()

    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysToGoalWeight)

    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = getMonthName(calendar.get(Calendar.MONTH))
    val year = calendar.get(Calendar.YEAR)



    return "$dayOfMonth $month $year"
}

fun getMonthName(month: Int): String {
    return DateFormatSymbols().months[month]
}