package com.example.healthlylife.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
): ViewModel() {

    private val _caloriesEaten = mutableDoubleStateOf(0.0)
    val caloriesEaten: State<Double> = _caloriesEaten

    private val _proteinsEaten = mutableDoubleStateOf(0.0)
    val proteinsEaten: State<Double> = _proteinsEaten

    private val _carbsEaten = mutableDoubleStateOf(0.0)
    val carbsEaten: State<Double> = _carbsEaten

    private val _fatEaten = mutableDoubleStateOf(0.0)
    val fatEaten: State<Double> = _fatEaten

    private val _bmr = mutableIntStateOf(0)
    val bmr: State<Int> = _bmr

    private val _caloriesBurned = mutableDoubleStateOf(0.0)
    val caloriesBurned: State<Double> = _caloriesBurned

    init {
        fetchEatenData()
    }

    private fun fetchEatenData() {
        val userId = auth.currentUser?.uid ?: return
        val userRef = db.collection("users").document(userId)
        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    _caloriesEaten.doubleValue = document.getDouble("caloriesEaten") ?: 0.0
                    _proteinsEaten.doubleValue = document.getDouble("proteinsEaten") ?: 0.0
                    _carbsEaten.doubleValue = document.getDouble("carbsEaten") ?: 0.0
                    _fatEaten.doubleValue = document.getDouble("fatEaten") ?: 0.0
                    _bmr.intValue = document.getLong("bmr")?.toInt() ?: 0
                    _caloriesBurned.doubleValue = document.getDouble("caloriesBurned") ?: 0.0
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Firestore data Error", exception)
            }
    }
}

