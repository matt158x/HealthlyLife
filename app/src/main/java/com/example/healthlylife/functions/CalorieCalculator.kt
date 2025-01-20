package com.example.healthlylife.functions

import com.example.healthlylife.data.UserFormData
import java.text.DateFormatSymbols
import java.util.Calendar
import kotlin.math.ceil


fun calculateCalorieIntake(userFormData: UserFormData) {

    val calorieSurplus = 500
    val calorieDeficit = 500

    fun calculateBMR(weight: Double, height: Double, age: Int, gender: String): Double {
        return if (gender == "Man") {
            10 * weight + 6.25 * height - 5 * age + 5
        } else {
            10 * weight + 6.25 * height - 5 * age - 161
        }
    }

    val bmr = calculateBMR(userFormData.currentWeight, userFormData.height, userFormData.age, userFormData.gender)
    val adjustedBmr = when (userFormData.goal) {
        "Gain Weight" -> bmr + calorieSurplus
        "Lose Weight" -> bmr - calorieDeficit
        else -> bmr
    }
    userFormData.bmr = adjustedBmr
}

fun calculateGoalCompletionDate(userFormData: UserFormData) {
    val goalWeight = userFormData.targetWeight
    val weeklyChange = if (userFormData.goal == "Gain Weight") 0.5 else -0.5
    val daysToGoalWeight = ceil((goalWeight - userFormData.currentWeight) / (weeklyChange / 7)).toInt()

    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysToGoalWeight)

    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = getMonthName(calendar.get(Calendar.MONTH))
    val year = calendar.get(Calendar.YEAR)

    val goalData = ("$dayOfMonth $month $year")
    userFormData.goalData = goalData
}

fun getMonthName(month: Int): String {
    return DateFormatSymbols().months[month]
}