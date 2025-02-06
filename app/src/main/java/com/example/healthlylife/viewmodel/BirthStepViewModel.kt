package com.example.healthlylife.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthlylife.data.UserFormDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BirthStepViewModel @Inject constructor(
    private val userFormDataStore: UserFormDataStore
) : ViewModel() {


    private var _birthDateInput = MutableStateFlow("")
    var birthDateInput: StateFlow<String> = _birthDateInput.asStateFlow()

    private var ageInput by mutableIntStateOf(0)

    init {
        viewModelScope.launch {
            userFormDataStore.birthDateFlow.collect { birthDate ->
                _birthDateInput.value = birthDate
            }
            userFormDataStore.ageFlow.collect { age ->
                ageInput = age
            }
        }
    }

    fun updateBirthDate(birthDate: String) {
        viewModelScope.launch {
            userFormDataStore.saveBirthDate(birthDate)
            _birthDateInput.value = birthDate
            userFormDataStore.saveAge(calculateAge(birthDate))
        }
    }

    private fun calculateAge(birthDate: String): Int{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val birthDateLocal = LocalDate.parse(birthDate, formatter)
        val currentDate = LocalDate.now()
        val period = Period.between(birthDateLocal, currentDate)

        return period.years
    }

}