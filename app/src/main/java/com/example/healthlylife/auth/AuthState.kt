package com.example.healthlylife.auth

sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object FormNotCompleted : AuthState()
    data object Loading : AuthState()

    data class Error(val message: String) : AuthState()
    data class PasswordMismatch(val message: String) : AuthState()
    data class UserExists(val message: String) : AuthState()
    data class InvalidCredentials(val message: String) : AuthState()
}