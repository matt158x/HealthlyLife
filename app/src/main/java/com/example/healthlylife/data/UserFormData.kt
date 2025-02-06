package com.example.healthlylife.data

import com.example.healthlylife.R

data class UserFormData(
    var goal: String = "",
    var gender: String = "",
    var birthDate: String = "",
    var currentWeight: Int = 0,
    var targetWeight: Int = 0,
    var height: Int = 0,
    var age: Int = 0,
    var goalData: String = "",
    var bmr: Int = 0,
    var formCompleted: Boolean = false,
    var chartImage: Int = R.drawable.wykres1,
)