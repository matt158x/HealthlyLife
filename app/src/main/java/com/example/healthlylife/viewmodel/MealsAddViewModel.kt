package com.example.healthlylife.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.healthlylife.data.MealData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealsAddViewModel @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth // Pobieranie aktualnie zalogowanego użytkownika
) : ViewModel() {

    var mealName by mutableStateOf("")
    var calories by mutableStateOf("")
    var protein by mutableStateOf("")
    var carbs by mutableStateOf("")
    var fat by mutableStateOf("")

    fun addMeal(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            onError("User not logged in")
            return
        }

        val meal = MealData(
            name = mealName,
            calories = calories.toDoubleOrNull() ?: 0.0,
            proteins = protein.toDoubleOrNull() ?: 0.0,
            carbs = carbs.toDoubleOrNull() ?: 0.0,
            fat = fat.toDoubleOrNull() ?: 0.0
        )

        db.collection("users").document(userId).collection("food")
            .document(mealName) // Ustawiamy ID dokumentu na nazwę posiłku!
            .set(meal)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onError(e.message ?: "Unknown error") }
    }
}