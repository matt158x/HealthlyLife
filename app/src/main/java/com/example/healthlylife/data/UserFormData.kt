package com.example.healthlylife.data

import com.example.healthlylife.R

data class UserFormData(
    var goal: String = "",
    var gender: String = "",
    var birthDate: String = "",
    var currentWeight: Double = 0.0,
    var targetWeight: Double = 0.0,
    var height: Double = 0.0,
    var chartImage: Int = R.drawable.wykres1,
    var age: Int = 0,
    var goalData: String = "",
    var bmr: Double = 0.0,
    var formCompleted: Boolean = false
)