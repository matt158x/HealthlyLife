package com.example.healthlylife.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserFormDataStore @Inject constructor(@ApplicationContext private val context: Context) {


    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_form_data")

    object PreferencesKeys {
        val GOAL_KEY = stringPreferencesKey("goal")
        val CHART_IMAGE_KEY = intPreferencesKey("chart_image")
        val GENDER_KEY = stringPreferencesKey("gender")
        val BIRTH_DATE_KEY = stringPreferencesKey("birth_date")
        val TARGET_WEIGHT_KEY = intPreferencesKey("target_weight")
        val HEIGHT_KEY = intPreferencesKey("height")
        val WEIGHT_KEY = intPreferencesKey("current_weight")
        val AGE_KEY = intPreferencesKey("age")
        val BMR_KEY = intPreferencesKey("bmr")
        val GOAL_DATA_KEY = stringPreferencesKey("goal_data")
    }

    suspend fun saveGoal(goal: String) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.GOAL_KEY] = goal
        }
    }

    suspend fun saveChartImage(chartImage: Int) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.CHART_IMAGE_KEY] = chartImage
        }
    }

    suspend fun saveGender(gender: String) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.GENDER_KEY] = gender
        }
    }

    suspend fun saveBirthDate(birthDate: String) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.BIRTH_DATE_KEY] = birthDate
        }
    }

    suspend fun saveAge(age: Int) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.AGE_KEY] = age
        }
    }

    suspend fun saveTargetWeight(targetWeight: Int) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.TARGET_WEIGHT_KEY] = targetWeight
        }
    }

    suspend fun saveHeight(height: Int) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.HEIGHT_KEY] = height
        }
    }

    suspend fun saveCurrentWeight(currentWeight: Int) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.WEIGHT_KEY] = currentWeight
        }
    }


    suspend fun saveBMR(bmr: Int) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.BMR_KEY] = bmr
        }
    }

    suspend fun saveGoalData(goalData: String) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.GOAL_DATA_KEY] = goalData
        }
    }

    val goalFlow: Flow<String> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.GOAL_KEY]?: ""
        }

    val chartImageFlow: Flow<Int> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.CHART_IMAGE_KEY]?: 0
        }

    val targetWeightFlow: Flow<Int> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.TARGET_WEIGHT_KEY]?: 0
        }

    val genderFlow: Flow<String> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.GENDER_KEY]?: ""
        }

    val birthDateFlow: Flow<String> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.BIRTH_DATE_KEY]?: ""
        }

    val ageFlow: Flow<Int> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.AGE_KEY]?: 0
        }

    val heightFlow: Flow<Int> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.HEIGHT_KEY]?: 0
        }

    val currentWeightFlow: Flow<Int> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.WEIGHT_KEY]?: 0
        }


    val bmrFlow: Flow<Int> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.BMR_KEY]?: 0
        }

    val goalDataFlow: Flow<String> = context.userDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.GOAL_DATA_KEY]?: ""
        }
}