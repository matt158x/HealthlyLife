package com.example.healthlylife.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.healthlylife.data.MealData
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MealsScreenViewModel @Inject constructor(
    private val db: FirebaseFirestore
) : ViewModel() {

    private val _foodList = mutableStateOf<List<String>>(emptyList())
    val foodList: State<List<String>> = _foodList

    private val _mealData = mutableStateOf(MealData("",0.0, 0.0, 0.0, 0.0))
    val mealData: State<MealData> = _mealData

    private val _mealName = mutableStateOf("")
    val mealName: State<String> = _mealName


    init {
        fetchFoodItems()
    }


    private fun fetchFoodItems() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val globalFoodRef = db.collection("food").get()
        val userFoodRef = db.collection("users").document(userId).collection("food").get()

        Tasks.whenAllSuccess<QuerySnapshot>(globalFoodRef, userFoodRef)
            .addOnSuccessListener { results ->
                val allFoodList = results.flatMap { it.documents.mapNotNull { doc -> doc.id } }
                _foodList.value = allFoodList.distinct()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting food list", exception)
            }
    }

    fun fetchMealDetails(mealId: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val globalMealRef = db.collection("food").document(mealId)
        val userMealRef = db.collection("users").document(userId).collection("food").document(mealId)

        globalMealRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    updateMealData(document)
                } else {
                    userMealRef.get()
                        .addOnSuccessListener { userDocument ->
                            if (userDocument.exists()) {
                                updateMealData(userDocument)
                            } else {
                                Log.e("Firestore", "Meal not found in global or user collection")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.e("Firestore", "Error fetching user meal details", exception)
                        }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error fetching global meal details", exception)
            }
    }

    private fun updateMealData(document: DocumentSnapshot) {
        val calories = document.getDouble("calories") ?: 0.0
        val fat = document.getDouble("fat") ?: 0.0
        val proteins = document.getDouble("proteins") ?: 0.0
        val carbs = document.getDouble("carbs") ?: 0.0

        _mealData.value = MealData("", calories, proteins, carbs, fat)
        _mealName.value = document.id
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

