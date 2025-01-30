package com.example.healthlylife.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.healthlylife.data.MealDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FoodViewModel @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _foodList = mutableStateOf<List<String>>(emptyList())
    val foodList: State<List<String>> = _foodList

    private val _mealDetails = mutableStateOf(MealDetails(0.0, 0.0, 0.0, 0.0))
    val mealDetails: State<MealDetails> = _mealDetails

    private val _caloriesEaten = mutableDoubleStateOf(0.0)
    val caloriesEaten: State<Double> = _caloriesEaten

    private val _proteinsEaten = mutableDoubleStateOf(0.0)
    val proteinsEaten: State<Double> = _proteinsEaten

    private val _carbsEaten = mutableDoubleStateOf(0.0)
    val carbsEaten: State<Double> = _carbsEaten

    private val _fatEaten = mutableDoubleStateOf(0.0)
    val fatEaten: State<Double> = _fatEaten

    init {
        fetchFoodItems()
        fetchEatenData()
    }


    private fun fetchFoodItems() {
        db.collection("food")
            .get()
            .addOnSuccessListener { documents ->
                _foodList.value = documents.mapNotNull { it.id }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Firestore data error", exception)
            }
    }

    fun fetchMealDetails(mealId: String) {
        db.collection("food")
            .document(mealId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val calories = document.getDouble("calories") ?: 0.0
                    val fat = document.getDouble("fat") ?: 0.0
                    val proteins = document.getDouble("proteins") ?: 0.0
                    val carbs = document.getDouble("carbs") ?: 0.0

                    _mealDetails.value = MealDetails(calories, proteins, carbs, fat)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Firestore food data error", exception)
            }
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
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Firestore data Error", exception)
            }
    }

    fun addMealToFirestore(
        adjustedCalories: Double,
        adjustedProteins: Double,
        adjustedCarbs: Double,
        adjustedFat: Double,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val userRef = db.collection("users").document(userId)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)

            val currentCalories = snapshot.getDouble("caloriesEaten") ?: 0.0
            val currentProteins = snapshot.getDouble("proteinsEaten") ?: 0.0
            val currentCarbs = snapshot.getDouble("carbsEaten") ?: 0.0
            val currentFat = snapshot.getDouble("fatEaten") ?: 0.0

            val newCalories = currentCalories + adjustedCalories
            val newProteins = currentProteins + adjustedProteins
            val newCarbs = currentCarbs + adjustedCarbs
            val newFat = currentFat + adjustedFat

            transaction.update(userRef, mapOf(
                "caloriesEaten" to newCalories,
                "proteinsEaten" to newProteins,
                "carbsEaten" to newCarbs,
                "fatEaten" to newFat
            ))
        }.addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener { e ->
            onError(e)
        }
    }
}

