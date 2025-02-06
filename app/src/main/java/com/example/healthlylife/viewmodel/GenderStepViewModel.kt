package com.example.healthlylife.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthlylife.data.UserFormDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderStepViewModel @Inject constructor(
    private val userFormDataStore: UserFormDataStore
) : ViewModel() {

    private val _selectedGender = MutableStateFlow("")
    val selectedGender: StateFlow<String> = _selectedGender.asStateFlow()

    init {
        viewModelScope.launch {
            userFormDataStore.genderFlow.collect { gender ->
                _selectedGender.value = gender
            }
        }
    }

    fun updateGender(gender: String) {
        viewModelScope.launch {
            userFormDataStore.saveGender(gender)
            _selectedGender.value = gender
        }
    }
}