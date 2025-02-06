package com.example.healthlylife.viewmodel

import androidx.lifecycle.ViewModel
import com.example.healthlylife.auth.AuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegisterScreenViewModel(
    private val sharedViewModel: SharedViewModel
) : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signup(email: String, password: String, confirmPassword: String) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            sharedViewModel.updateAuthState(AuthState.Error("All fields are required"))
            return
        }
        if (password != confirmPassword) {
            sharedViewModel.updateAuthState(AuthState.PasswordMismatch("Passwords do not match"))
            return
        }
        sharedViewModel.updateAuthState(AuthState.Loading)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Po udanej rejestracji sprawdź status użytkownika
                    sharedViewModel.checkAuthStatus()
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthUserCollisionException -> "User already exists. Please log in."
                        else -> task.exception?.message ?: "Something went wrong"
                    }
                    sharedViewModel.updateAuthState(AuthState.UserExists(errorMessage))
                }
            }
    }
}