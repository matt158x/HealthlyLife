package com.example.healthlylife.viewmodel

import androidx.lifecycle.ViewModel
import com.example.healthlylife.auth.AuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {


    fun login(email: String, password: String, sharedViewModel: SharedViewModel) {
        if (email.isEmpty() || password.isEmpty()) {
            sharedViewModel.updateAuthState(AuthState.Error("Email or password can't be empty"))
            return
        }
        sharedViewModel.updateAuthState(AuthState.Loading)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    sharedViewModel.checkAuthStatus()
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "User not found. Please register."
                        is FirebaseAuthInvalidCredentialsException -> "Invalid email or password."
                        else -> task.exception?.message ?: "Something went wrong"
                    }
                    sharedViewModel.updateAuthState(AuthState.InvalidCredentials(errorMessage))
                }
            }
    }
}