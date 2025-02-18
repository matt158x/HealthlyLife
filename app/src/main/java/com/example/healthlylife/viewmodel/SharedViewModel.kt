package com.example.healthlylife.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthlylife.auth.AuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SharedViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            checkFormCompletionStatus(currentUser.uid)
        }
    }

    private fun checkFormCompletionStatus(userId: String) {
        _authState.value = AuthState.Loading
        FirebaseFirestore.getInstance().collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists() && document.getBoolean("formCompleted") == true) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.FormNotCompleted
                }
            }
            .addOnFailureListener { e ->
                _authState.value = AuthState.Error(e.message ?: "Failed to fetch user data")
            }
    }

    fun updateAuthState(state: AuthState) {
        _authState.value = state
    }


}