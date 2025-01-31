package com.example.healthlylife.viewmodel

import androidx.lifecycle.ViewModel
import com.example.healthlylife.data.UserFormData
import com.example.healthlylife.data.UserFormRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class UserFormViewModel @Inject constructor(
    private val repository: UserFormRepository
) : ViewModel() {

    val userFormData: UserFormData
        get() = repository.getUserFormData()

    fun updateGoal(goal: String, chartImage: Int) {
        repository.updateGoal(goal, chartImage)
    }

    fun updateGender(gender:String) {
        repository.updateGender(gender)
    }

    fun updateBirthDate(birthDate: String) {
        val age = calculateAge(birthDate)
        repository.updateBirthDate(birthDate, age)
    }


    fun updateInfo(currentWeight: Int, targetWeight: Int, height: Int) {
        repository.updateInfo(currentWeight, targetWeight, height)
        calculateCalorieIntake(userFormData)
        calculateGoalCompletionDate(userFormData)
    }

    fun completeForm(onComplete: (Boolean) -> Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            userFormData.formCompleted = true
            FirebaseFirestore.getInstance()
                .collection("users")
                .document(userId)
                .set(userFormData)
                .addOnSuccessListener {
                    println("Register successful")
                    onComplete(true)
                }
                .addOnFailureListener { e ->
                    println("Data registration failed: ${e.message}")
                    onComplete(false)
                }
        } else {
            println("User ID is null")
            onComplete(false)
        }
    }

    private fun calculateAge(birthDate: String): Int{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val birthDateLocal = LocalDate.parse(birthDate, formatter)
        val currentDate = LocalDate.now()
        val period = Period.between(birthDateLocal, currentDate)

        return period.years
    }

    private fun calculateCalorieIntake(userFormData: UserFormData) {
        val calorieSurplus = 500
        val calorieDeficit = 500

        fun calculateBMR(weight: Int, height: Int, age: Int, gender: String): Double {
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
        userFormData.bmr = adjustedBmr.toInt()
    }

    private fun calculateGoalCompletionDate(userFormData: UserFormData) {
        val goalWeight = userFormData.targetWeight
        val weeklyChange = if (userFormData.goal == "Gain Weight") 0.5 else -0.5
        val daysToGoalWeight = ceil((goalWeight - userFormData.currentWeight) / (weeklyChange / 7)).toInt()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, daysToGoalWeight)

        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = getMonthName(calendar.get(Calendar.MONTH))
        val year = calendar.get(Calendar.YEAR)

        val goalData = "$dayOfMonth $month $year"
        userFormData.goalData = goalData
    }

    private fun getMonthName(month: Int): String {
        return DateFormatSymbols().months[month]
    }
}
