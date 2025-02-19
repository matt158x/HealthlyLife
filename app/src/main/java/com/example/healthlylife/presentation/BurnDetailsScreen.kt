package com.example.healthlylife.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomBurnButton
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.viewmodel.BurnDetailsScreenViewModel

@Composable
fun BurnDetailsScreen(navController: NavController, activityType: String, viewModel: BurnDetailsScreenViewModel = hiltViewModel()) {

    val context = LocalContext.current

    var selectedButtonIndex by remember { mutableIntStateOf(-1) }

    val metValues = when (activityType) {
        "Running" -> listOf(3.0, 8.0, 12.0)
        "Cycling" -> listOf(4.0, 6.0, 9.0, 13.0)
        "Swimming" -> listOf(4.5, 7.5, 3.0)
        "Roller skating" -> listOf(4.5, 7.0, 12.0)
        "Workout" -> listOf(3.0, 4.5, 6.5)
        else -> listOf(0.0, 0.0, 0.0)
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181414))
    ) {

        Spacer(modifier=Modifier.height(60.dp))

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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = activityType,
                fontSize = 28.sp,
                color = Color.White,
                fontFamily = alkatrFontFamily
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = viewModel.timeInput,
                onValueChange = { viewModel.updateTime(it) },
                label = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Enter time - minutes", color = Color.Gray)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = Color.White,
                    textAlign = TextAlign.Center),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF292929),
                    focusedContainerColor = Color(0xFF292929),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Intensity",
                fontSize = 28.sp,
                fontFamily = alkatrFontFamily,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            metValues.forEachIndexed { index, met ->
                CustomBurnButton(
                    isSelected = selectedButtonIndex == index,
                    onClick = {
                        selectedButtonIndex = index
                        viewModel.selectMET(met) },
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292929),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)

                ) {
                    val intensity = when (activityType) {
                        "Running" -> listOf("Walking", "Jogging", "Sprint")
                        "Cycling" -> listOf("Slow road riding", "Medium speed road riding", "High speed road riding", "Mountain riding (MTB)")
                        "Swimming" -> listOf("Calm swim", "Intense swimming", "Water gymnastics")
                        "Roller skating" -> listOf("Slow", "Medium", "Fast")
                        "Workout" -> listOf("Light strength training", "Medium strength training", "Intense strength training")
                        else -> listOf("Low", "Medium", "High")
                    }
                    Text(
                        text = intensity[index],
                        color = Color.White,
                        fontSize = 18.sp,)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Estimated calories burned",
                    fontSize = 16.sp,
                    color = Color.Green,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "${viewModel.caloriesBurned.toInt()} kcal",
                    fontSize = 20.sp,
                    color = Color.Green,
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.height(50.dp))

            CustomButton(
                onClick = {
                    viewModel.saveCalories { isSuccess ->
                        if (isSuccess) {
                            navController.navigate("home")
                        } else {
                            Toast.makeText(context, "Error saving calories", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                text = "Save",
                textColor = Color.Black,
                textSize = 18.sp
            )

        }
    }
}