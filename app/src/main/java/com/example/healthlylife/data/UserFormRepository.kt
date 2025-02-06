package com.example.healthlylife.data

import com.example.healthlylife.di.FirebaseUserDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserFormRepository @Inject constructor(
    private val userFormDataStore: UserFormDataStore,
    private val firebaseUserDataSource: FirebaseUserDataSource
) {

    fun getUserDataFlow(): Flow<UserFormData> {
        return combine(
            listOf(
                userFormDataStore.goalFlow,
                userFormDataStore.chartImageFlow,
                userFormDataStore.genderFlow,
                userFormDataStore.birthDateFlow,
                userFormDataStore.ageFlow,
                userFormDataStore.targetWeightFlow,
                userFormDataStore.heightFlow,
                userFormDataStore.currentWeightFlow,
                userFormDataStore.bmrFlow,
                userFormDataStore.goalDataFlow
            )
        ) { values ->
            UserFormData(
                goal = values[0] as String,
                chartImage = values[1] as Int,
                gender = values[2] as String,
                birthDate = values[3] as String,
                age = values[4] as Int,
                targetWeight = values[5] as Int,
                height = values[6] as Int,
                currentWeight = values[7] as Int,
                bmr = values[8] as Int,
                goalData = values[9] as String
            )
        }
    }

    fun saveUserDataToFirebase(userId: String, onComplete: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userData = getUserDataFlow().first()
                firebaseUserDataSource.saveUserData(userId, userData, onComplete)
            } catch (e: Exception) {
                onComplete(false)
            }
        }
    }
}
