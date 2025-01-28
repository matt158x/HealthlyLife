package com.example.healthlylife.form

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.presentation.damionFontFamily
import com.example.healthlylife.viewmodel.UserFormViewModel


@Composable
fun InfoStep(
    modifier: Modifier = Modifier,
    viewModel: UserFormViewModel = hiltViewModel(),
    onNext: () -> Unit,
    onBack: () -> Unit
) {

    val goal = viewModel.userFormData.goal
    var weight by remember { mutableIntStateOf(80) }
    var targetWeight by remember { mutableIntStateOf(80) }
    var height by remember { mutableIntStateOf(170) }


    val targetWeightRange = when (goal) {
        "Gain Weight" -> weight.toFloat()..130f
        "Lose Weight" -> 30f..weight.toFloat()
        else -> 30f..130f
    }

    val initialTargetWeight = when (goal) {
        "Gain Weight" -> (weight + 130) / 2
        "Lose Weight" -> (30 + weight) / 2
        else -> 80f
    }

    targetWeight = initialTargetWeight.toInt()

    val sliderColors = SliderDefaults.colors(
        thumbColor = Color(0xFF32CD32),
        activeTrackColor = Color(0xFF32CD32),
        inactiveTrackColor = Color(0xFF616161),
        activeTickColor = Color(0xFF32CD32),
        inactiveTickColor = Color(0xFF616161)
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFF181414)
    ) {
        Box(modifier = modifier.fillMaxSize()) {

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
                    text = "What is your height ?",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(16.dp))


                Slider(
                    value = height.toFloat(),
                    onValueChange = { height = it.toInt() },
                    valueRange = 120f..220f,
                    steps = 100,
                    colors = sliderColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$height cm",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "What is your current weight ?",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                Slider(
                    value = weight.toFloat(),
                    onValueChange = { weight = it.toInt() },
                    valueRange = 30f..130f,
                    steps = 110,
                    colors = sliderColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$weight kg",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "What is your target weight ?",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(16.dp))


                Slider(
                    value = targetWeight.toFloat(),
                    onValueChange = { newValue ->

                        targetWeight = newValue.toInt().coerceIn(targetWeightRange.start.toInt(), targetWeightRange.endInclusive.toInt())
                    },
                    valueRange = targetWeightRange,
                    steps = 110,
                    colors = sliderColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$targetWeight kg",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "We use this information to calculate and provide you with \n daily personalized recommendations",
                    color = Color(0xFF616161),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(onClick = {
                    viewModel.updateInfo(weight, targetWeight, height)
                    onNext()
                },
                    text = "NEXT",
                    textColor = Color.Black,
                    textSize = 18.sp
                )
            }
        }
    }
}