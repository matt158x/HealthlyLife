package com.example.healthlylife.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthlylife.data.UserFormData
import com.example.healthlylife.data.UserFormDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DateFormatSymbols
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.ceil

@HiltViewModel
class InfoStepViewModel @Inject constructor(
    private val userFormDataStore: UserFormDataStore
) : ViewModel() {

    private var _selectedCurrentWeight = MutableStateFlow(0)
    val selectedCurrentWeight: StateFlow<Int> = _selectedCurrentWeight.asStateFlow()

    private var _selectedTargetWeight = MutableStateFlow(0)
    val selectedTargetWeightFlow: StateFlow<Int> = _selectedTargetWeight.asStateFlow()

    private var _selectedHeight = MutableStateFlow(0)
    val selectedHeightFlow: StateFlow<Int> = _selectedHeight.asStateFlow()

    private val _userFormData = mutableStateOf(UserFormData())

    val goal: StateFlow<String> = userFormDataStore.goalFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )


    init {
        viewModelScope.launch {
            userFormDataStore.currentWeightFlow.collect { currentWeight ->
                _selectedCurrentWeight.value = currentWeight
            }
            userFormDataStore.targetWeightFlow.collect { targetWeight ->
                _selectedTargetWeight.value = targetWeight
            }
            userFormDataStore.heightFlow.collect { height ->
                _selectedHeight.value = height
            }
            userFormDataStore.bmrFlow.collect { bmr ->
                _userFormData.value = _userFormData.value.copy(bmr = bmr)
            }
            userFormDataStore.goalDataFlow.collect { goalData ->
                _userFormData.value = _userFormData.value.copy(goalData = goalData)
            }
            userFormDataStore.genderFlow.collect { gender ->
                    _userFormData.value = _userFormData.value.copy(gender = gender)
            }
            userFormDataStore.ageFlow.collect { age ->
                    _userFormData.value = _userFormData.value.copy(age = age)
            }
        }
    }

    fun updateSelectedCurrentWeight(currentWeight: Int) {
        viewModelScope.launch {
            userFormDataStore.saveCurrentWeight(currentWeight)
            _selectedCurrentWeight.value = currentWeight
        }
    }

    fun updateSelectedTargetWeight(targetWeight: Int) {
        viewModelScope.launch {
            userFormDataStore.saveTargetWeight(targetWeight)
            _selectedTargetWeight.value = targetWeight
        }
    }

    fun updateSelectedHeight(height: Int) {
        viewModelScope.launch {
            userFormDataStore.saveHeight(height)
            _selectedHeight.value = height
        }
    }

    fun calculateAll() {
        viewModelScope.launch {
            val gender = userFormDataStore.genderFlow.first()
            val age = userFormDataStore.ageFlow.first()
            val goal = goal.value

            _userFormData.value = _userFormData.value.copy(
                currentWeight = _selectedCurrentWeight.value,
                targetWeight = _selectedTargetWeight.value,
                height = _selectedHeight.value,
                gender = gender,
                age = age,
                goal = goal
            )

            calculateCalorieIntake(_userFormData.value)
            calculateGoalCompletionDate(_userFormData.value)

            userFormDataStore.saveBMR(_userFormData.value.bmr)
            userFormDataStore.saveGoalData(_userFormData.value.goalData)
        }
    }

    private fun calculateCalorieIntake(userFormData: UserFormData) {
        val calorieSurplus = 500
        val calorieDeficit = 500

        fun calculateBMR(weight: Int, height: Int, age: Int, gender: String): Double {
            return if (gender == "Man") {
                10 * weight + 6.25 * height - 5 * age + 5
            } else {
                10 * weight + 6.25 * height - 5 * age - 161
            }
        }

        val bmr = calculateBMR(
            weight = userFormData.currentWeight,
            height = userFormData.height,
            age = userFormData.age,
            gender = userFormData.gender
        )

        val adjustedBmr = when (userFormData.goal) {
            "Gain Weight" -> bmr + calorieSurplus
            "Lose Weight" -> bmr - calorieDeficit
            else -> bmr
        }

        userFormData.bmr = adjustedBmr.toInt()
    }

    private fun calculateGoalCompletionDate(userFormData: UserFormData) {
            if (userFormData.goal == "Maintain Weight") {
                _userFormData.value = _userFormData.value.copy(goalData = "")
                return
            }

            val goalWeight = userFormData.targetWeight
            val currentWeight = userFormData.currentWeight
            val weeklyChange = if (userFormData.goal == "Gain Weight") 0.5 else -0.5

            val weightDifference = goalWeight - currentWeight
            if (weightDifference.toDouble() == 0.0) {
                _userFormData.value = _userFormData.value.copy(goalData = "N/A")
                return
            }

            val daysToGoalWeight = ceil(abs(weightDifference) / (abs(weeklyChange) / 7)).toInt().coerceAtLeast(1)

            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, daysToGoalWeight)

            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val month = getMonthName(calendar.get(Calendar.MONTH))
            val year = calendar.get(Calendar.YEAR)

            val goalData = "$dayOfMonth $month $year"

            _userFormData.value = _userFormData.value.copy(goalData = goalData)
        }

    private fun getMonthName(month: Int): String {
        return DateFormatSymbols().months[month]
    }
}