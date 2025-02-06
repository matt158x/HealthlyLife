package com.example.healthlylife.viewmodel

import androidx.lifecycle.ViewModel
import com.example.healthlylife.auth.AuthState
import com.google.firebase.auth.FirebaseAuth

class ProfileScreenViewModel(
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        sharedViewModel.updateAuthState(AuthState.Unauthenticated)
    }
}
