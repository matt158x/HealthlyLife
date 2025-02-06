package com.example.healthlylife.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthlylife.data.UserFormData
import com.example.healthlylife.data.UserFormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SummaryStepViewModel @Inject constructor(
    private val userFormRepository: UserFormRepository
) : ViewModel() {

    private val _userFormData = mutableStateOf<UserFormData?>(null)
    val userFormData: State<UserFormData?> = _userFormData

    init {
        viewModelScope.launch {

            userFormRepository.getUserDataFlow().collect { data ->
                _userFormData.value = data
            }
        }
    }
}
