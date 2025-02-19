package com.example.healthlylife.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class BurnDetailsScreenViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private var weight by mutableDoubleStateOf(0.0)
    private var selectedMET by mutableDoubleStateOf(0.0)
    var caloriesBurned by mutableDoubleStateOf(0.0)
    var timeInput by mutableStateOf("")

    init {
        loadUserWeight()
    }

    private fun loadUserWeight() {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val fetchedWeight = document.getDouble("currentWeight") ?: 0.0
                weight = fetchedWeight
            }
            .addOnFailureListener {
                Log.e("BurnDetailsViewModel", "Failed to load weight", it)
            }
    }


    fun updateTime(input: String) {
        timeInput = input
        calculateCalories()
    }

    fun selectMET(met: Double) {
        selectedMET = met
        calculateCalories()
    }

    private fun calculateCalories() {
        val timeMinutes = timeInput.toDoubleOrNull() ?: 0.0
        val timeHours = timeMinutes / 60.0
        val newCalories = (selectedMET * weight * timeHours).roundToInt().toDouble()

        Log.d("BurnDetailsViewModel", "Time: $timeMinutes, Weight: $weight, MET: $selectedMET, Calories: $newCalories")

        if (caloriesBurned != newCalories) {
            caloriesBurned = newCalories
        }
    }

    fun saveCalories(onResult: (Boolean) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("users").document(userId)
            .update("caloriesBurned", FieldValue.increment(caloriesBurned))
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

}
