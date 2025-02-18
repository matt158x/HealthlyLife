package com.example.healthlylife.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.healthlylife.auth.AuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {


    private val _goal = mutableStateOf("")
    val goal: State<String> =_goal

    private val _weight = mutableIntStateOf(0)
    val weight: State<Int> = _weight

    private val _targetWeight = mutableIntStateOf(0)
    val targetWeight: State<Int> = _targetWeight

    private val _height = mutableIntStateOf(0)
    val height: State<Int> = _height

    private val _age = mutableIntStateOf(0)
    val age: State<Int> = _age

    private val _gender = mutableStateOf("")
    val gender: State<String> = _gender

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    init {
        fetchUserData()
    }


    private fun fetchUserData() {
        val user = auth.currentUser
        if (user != null) {
            _email.value = user.email ?: ""
        }

        val userId = auth.currentUser?.uid ?: return
        val userRef = db.collection("users").document(userId)
        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    _goal.value = document.getString("goal") ?: ""
                    _weight.intValue = document.getLong("currentWeight")?.toInt() ?: 0
                    _targetWeight.intValue = document.getLong("targetWeight")?.toInt() ?: 0
                    _height.intValue = document.getLong("height")?.toInt() ?: 0
                    _age.intValue = document.getLong("age")?.toInt() ?: 0
                    _gender.value = document.getString("gender") ?: ""
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Firestore data Error", exception)
            }
    }


    fun signOut(sharedViewModel: SharedViewModel) {
        FirebaseAuth.getInstance().signOut()
        sharedViewModel.updateAuthState(AuthState.Unauthenticated)
    }
}


