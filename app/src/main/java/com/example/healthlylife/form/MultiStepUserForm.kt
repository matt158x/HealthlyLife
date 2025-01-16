package com.example.healthlylife.form

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.healthlylife.data.UserFormData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun MultiStepUserForm(navController: NavController) {

    var currentStep by remember { mutableIntStateOf(1) }
    val userFormData = remember { UserFormData() }
    val firestore = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    Surface(modifier = Modifier.fillMaxSize()) {
        when (currentStep) {
            1 -> GoalStep(
                userFormData = userFormData,
                navController = navController,
                onNext = { currentStep = 2
                }
            )
            2 -> GenderStep(
                userFormData = userFormData,
                onNext = { currentStep = 3 },
                navController = navController
            )
            3 -> BirthDateStep(
                userFormData = userFormData,
                onNext = { currentStep = 4 },
                navController = navController
            )
            4 -> InfoStep(
                userFormData = userFormData,
                onNext = { currentStep = 5 },
                navController = navController
            )
            5 -> SummaryStep(
                userFormData = userFormData,
                onBack = { currentStep = 4 },
                navController = navController,
                onSubmit = {
                    if (userId != null) {
                        firestore.collection("users")
                            .document(userId)
                            .set(userFormData)
                            .addOnSuccessListener {
                                println("Register successful")
                                navController.navigate("home") {
                                    popUpTo("multistepform") { inclusive = true }
                                }
                            }
                            .addOnFailureListener { e ->
                                println("Data registration failed: ${e.message}")
                            }
                    }
                }
            )
        }
    }
}