package com.example.healthlylife.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserFormRepository @Inject constructor() {
    private var userFormData = UserFormData()

    fun getUserFormData(): UserFormData = userFormData

    fun updateGoal(goal: String, chartImage: Int) {
        userFormData.goal = goal
        userFormData.chartImage = chartImage
    }

    fun updateGender(gender: String){
        userFormData.gender = gender
    }

    fun updateBirthDate(birthDate: String, age: Int) {
        userFormData.birthDate = birthDate
        userFormData.age = age
    }

    fun updateInfo(currentWeight: Int, targetWeight: Int, height: Int) {
        userFormData.currentWeight = currentWeight
        userFormData.targetWeight = targetWeight
        userFormData.height = height
    }
}