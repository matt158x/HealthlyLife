package com.example.healthlylife.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.viewmodel.InfoStepViewModel


@Composable
fun InfoStep(
    viewModel: InfoStepViewModel = hiltViewModel(),
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val goal by viewModel.goal.collectAsState()
    val selectedCurrentWeight by viewModel.selectedCurrentWeight.collectAsState(80)
    val selectedHeight by viewModel.selectedHeightFlow.collectAsState(180)
    val context = LocalContext.current

    val selectedTargetWeight by viewModel.selectedTargetWeightFlow.collectAsState(
        if (goal == "Maintain Weight") selectedCurrentWeight else 80
    )

    val targetWeightRange = when (goal) {
        "Gain Weight" -> selectedCurrentWeight.toFloat()..200f
        "Lose Weight" -> 0f..selectedCurrentWeight.toFloat()
        "Maintain Weight" -> selectedCurrentWeight.toFloat()..selectedCurrentWeight.toFloat()
        else -> 0f..130f
    }

    val sliderColors = SliderDefaults.colors(
        thumbColor = Color(0xFF32CD32),
        activeTrackColor = Color(0xFF32CD32),
        inactiveTrackColor = Color(0xFF616161),
        activeTickColor = Color(0xFF32CD32),
        inactiveTickColor = Color(0xFF616161)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF181414))
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        IconButton(
            onClick = { onBack() },
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
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Your Information's",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 32.sp

            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Height?",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Slider(
                value = selectedHeight.toFloat(),
                onValueChange = { newHeight ->
                    viewModel.updateSelectedHeight(newHeight.toInt())
                },
                valueRange = 120f..220f,
                steps = 100,
                colors = sliderColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "$selectedHeight cm",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(30.dp))


            Text(
                text = "Current Weight",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Slider(
                value = selectedCurrentWeight.toFloat(),
                onValueChange = { newWeight ->
                    viewModel.updateSelectedCurrentWeight(newWeight.toInt())
                },
                valueRange = 0f..200f,
                steps = 200,
                colors = sliderColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "$selectedCurrentWeight kg",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(30.dp))


            if (goal != "Maintain Weight") {
                Text(
                    text = "Goal Weight",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                Slider(
                    value = selectedTargetWeight.toFloat(),
                    onValueChange = { newTargetWeight ->
                        viewModel.updateSelectedTargetWeight(newTargetWeight.toInt())
                    },
                    valueRange = targetWeightRange,
                    steps = 50,
                    colors = sliderColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$selectedTargetWeight kg",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "We use this information to calculate and provide you with \n daily personalized recommendations",
                color = Color(0xFF616161),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )


            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                onClick = {
                    if (selectedHeight == 0 || selectedCurrentWeight == 0 ||
                        (goal != "Maintain Weight" && selectedTargetWeight == 0)) {
                        Toast.makeText(context, "Enter your data", Toast.LENGTH_SHORT).show()
                    } else {
                        if (goal == "Gain Weight" && selectedTargetWeight <= selectedCurrentWeight) {
                            Toast.makeText(context, "Target weight must be greater than current weight", Toast.LENGTH_SHORT).show()
                        } else if (goal == "Lose Weight" && selectedTargetWeight >= selectedCurrentWeight) {
                            Toast.makeText(context, "Target weight must be less than current weight", Toast.LENGTH_SHORT).show()
                        } else {
                            if (goal == "Maintain Weight") {
                                viewModel.updateSelectedTargetWeight(selectedCurrentWeight)
                            } else {
                                viewModel.updateSelectedTargetWeight(selectedTargetWeight)
                            }


                            viewModel.updateSelectedCurrentWeight(selectedCurrentWeight)
                            viewModel.updateSelectedHeight(selectedHeight)
                            viewModel.calculateAll()
                            onNext()
                        }
                    }
                },
                text = "NEXT",
                textColor = Color.Black,
                textSize = 18.sp
            )
        }
    }
}