package com.example.healthlylife.di

import android.util.Log
import com.example.healthlylife.data.UserFormData
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseUserDataSource @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    fun saveUserData(userId: String, userData: UserFormData, onComplete: (Boolean) -> Unit) {

        userData.formCompleted = true

        db.collection("users").document(userId)
            .set(userData)
            .addOnSuccessListener {
                Log.d("Firebase", "Dane użytkownika zapisane pomyślnie")
                onComplete(true)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Błąd podczas zapisu danych", exception)
                onComplete(false)
            }
    }
}
