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
class GoalStepViewModel @Inject constructor(
    private val userFormDataStore: UserFormDataStore
) : ViewModel() {

    private val _selectedGoal = MutableStateFlow("")
    val selectedGoal: StateFlow<String> = _selectedGoal.asStateFlow()

    private val _selectedChartImage = MutableStateFlow(0)
    val selectedChartImage: StateFlow<Int> = _selectedChartImage.asStateFlow()


    init {
        viewModelScope.launch {
            userFormDataStore.goalFlow.collect { goal ->
                _selectedGoal.value = goal
            }
            userFormDataStore.chartImageFlow.collect { chartImage ->
                _selectedChartImage.value = chartImage
            }
        }
    }


    fun updateGoal(goal: String) {
        viewModelScope.launch {
            userFormDataStore.saveGoal(goal)
            _selectedGoal.value = goal
        }
    }

    fun updateChartImage(chartImage: Int) {
        viewModelScope.launch {
            userFormDataStore.saveChartImage(chartImage)
            _selectedChartImage.value = chartImage
        }
    }
}