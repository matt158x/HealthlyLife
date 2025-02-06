package com.example.healthlylife.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthlylife.data.UserFormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultiStepUserFormViewModel @Inject constructor(
    private val repository: UserFormRepository
) : ViewModel() {


    fun completeForm(userId: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repository.saveUserDataToFirebase(userId) { success ->
                    if (success) {
                        println("Data saved successfully and form marked as completed")
                        onComplete(true)
                    } else {
                        println("Data registration failed")
                        onComplete(false)
                    }
                }
            } catch (e: Exception) {
                println("Error fetching data: ${e.message}")
                onComplete(false)
            }
        }
    }
}