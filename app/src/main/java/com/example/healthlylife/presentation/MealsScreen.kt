package com.example.healthlylife.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomAddButton
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.components.MacroNutrientItem
import com.example.healthlylife.viewmodel.MealsScreenViewModel
import java.util.Locale

@Composable
fun MealsScreen(
    navController: NavController,
    viewModel: MealsScreenViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    var isDropdownOpen by remember { mutableStateOf(false) }
    val filteredList = viewModel.foodList.value.filter { it.contains(searchText, ignoreCase = true) }
    val mealDetails = viewModel.mealData.value
    val mealName = viewModel.mealName.value

    var percentage by remember { mutableIntStateOf(100) }
    val adjustedCalories = mealDetails.calories * percentage / 100
    val adjustedProteins = mealDetails.proteins * percentage / 100
    val adjustedCarbs = mealDetails.carbs * percentage / 100
    val adjustedFat = mealDetails.fat * percentage / 100

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF181414))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    isDropdownOpen = false
                    focusManager.clearFocus()
                })
            }
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 50.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(Modifier.height(100.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    isDropdownOpen = true
                },
                label = { Text("Search food") },
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { focusState ->
                        isDropdownOpen = focusState.isFocused
                    },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF292929),
                    focusedContainerColor = Color(0xFF292929),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp)
            )

                Spacer(modifier = Modifier.width(8.dp))

                CustomAddButton(
                    modifier = Modifier
                        .height(50.dp)
                        .width(60.dp)
                        .background(Color(0xFF292929), shape = RoundedCornerShape(12.dp)),
                    text = "+",
                    onClick = { navController.navigate("meals_add") }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                if (isDropdownOpen && filteredList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = Color(0xFF292929))
                    ) {
                        items(filteredList) { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = item,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp)
                                        .clickable {
                                            searchText = item
                                            isDropdownOpen = false
                                            focusManager.clearFocus()
                                            viewModel.fetchMealDetails(item)
                                        }
                                )
                            }
                        }
                    }
                }
            }


            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = mealName,
                color = Color.White,
                fontSize = 26.sp,
                fontFamily = submarinerFontFamily
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFF292929), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                MacroNutrientItem(label = "Calories", value = String.format(Locale.US, "%.1f", adjustedCalories), modifier = Modifier.padding(top = 5.dp))
                MacroNutrientItem(label = "Proteins", value = String.format(Locale.US, "%.1f", adjustedProteins), modifier = Modifier.padding(top = 5.dp))
                MacroNutrientItem(label = "Carbs", value = String.format(Locale.US, "%.1f", adjustedCarbs), modifier = Modifier.padding(top = 5.dp))
                MacroNutrientItem(label = "Fat", value = String.format(Locale.US, "%.1f", adjustedFat), modifier = Modifier.padding(top = 5.dp))
            }

            Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomAddButton(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "-",
                    onClick = { if (percentage > 5) percentage -= 5 }
                )

                Text(
                    text = "$percentage g",
                    color = Color.White,
                    fontSize = 22.sp
                )

                CustomAddButton(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "+",
                    onClick = { percentage += 5 }
                )
            }

            Spacer(Modifier.height(50.dp))

            CustomButton(
                text = "ADD",
                textColor = Color.Black,
                textSize = 18.sp,
                onClick = {
                    viewModel.addMealToFirestore(
                        adjustedCalories = adjustedCalories,
                        adjustedProteins = adjustedProteins,
                        adjustedCarbs = adjustedCarbs,
                        adjustedFat = adjustedFat,
                        onSuccess = {
                            navController.navigate("home") {
                                popUpTo("meals") { inclusive = true }
                            }
                        },
                        onError = { e ->
                            Log.e("Firestore", "Firestore data error", e)
                        }
                    )
                },
            )
        }
    }
}