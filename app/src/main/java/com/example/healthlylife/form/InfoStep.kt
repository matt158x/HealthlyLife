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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.data.UserFormData
import com.example.healthlylife.presentation.damionFontFamily


@Composable
fun InfoStep(modifier: Modifier = Modifier, navController: NavController, userFormData: UserFormData, onNext: () -> Unit) {


        var weight by remember { mutableStateOf(80f) }
        var targetWeight by remember { mutableStateOf(80f) }
        var height by remember { mutableStateOf(170f) }

        val sliderColors = SliderDefaults.colors(
        thumbColor = Color(0xFF32CD32),  // Kolor kropki (thumb) suwaka
        activeTrackColor = Color(0xFF32CD32),  // Kolor aktywnej części suwaka
        inactiveTrackColor = Color(0xFF616161),  // Kolor nieaktywnej części suwaka
        activeTickColor = Color(0xFF32CD32),  // Kolor aktywnych ticków
        inactiveTickColor = Color(0xFF616161)  // Kolor nieaktywnych ticków
    )

        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color(0xFF181414)
        ) {
            Box(modifier = modifier.fillMaxSize()) {

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
                        value = height,
                        onValueChange = { height = it },
                        valueRange = 120f..220f,
                        steps = 100,
                        colors = sliderColors,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${height.toInt()} cm",
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
                        value = weight,
                        onValueChange = { weight = it },
                        valueRange = 30f..130f,
                        steps = 110,
                        colors = sliderColors,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${weight.toInt()} kg",
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
                        value = targetWeight,
                        onValueChange = { targetWeight = it },
                        valueRange = 30f..130f,
                        steps = 110,
                        colors = sliderColors,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${targetWeight.toInt()} kg",
                        fontFamily = damionFontFamily,
                        color = Color.White,
                        fontSize = 22.sp
                    )



                    Spacer(modifier = Modifier.height(50.dp))

                    Text(
                        text = "We use this information to calculate and provice you with \n daily perosnalized reccomendations",
                        color = Color(0xFF616161),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center

                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomButton(onClick = {
                        userFormData.currentWeight = weight.toDouble()
                        userFormData.targetWeight = targetWeight.toDouble()
                        userFormData.height = height.toDouble()
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

