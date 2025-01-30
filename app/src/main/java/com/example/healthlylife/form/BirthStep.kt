package com.example.healthlylife.form

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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.presentation.damionFontFamily
import com.example.healthlylife.viewmodel.UserFormViewModel
import com.example.healthlylife.wheelPickerFork.WheelDatePickerComponent.WheelDatePicker
import com.example.healthlylife.wheelPickerFork.WheelPickerDefaults

@Composable
fun BirthDateStep(
    viewModel: UserFormViewModel = hiltViewModel(),
    onNext: () -> Unit,
    onBack: () -> Unit
)
{
    var birthDateInput by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181414))
    )
    {
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
                    text = "What is your birth date ?",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CompositionLocalProvider(LocalContentColor provides Color.White){
                WheelDatePicker(
                    modifier = Modifier.fillMaxWidth(),
                    dateTextColor = Color(0xFF32CD32),
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        borderColor = Color.White,
                    ),
                    rowCount = 5,
                    height = 250.dp,
                    dateTextStyle = TextStyle(
                        fontWeight = FontWeight(600),
                        color = Color.White,
                        shadow = Shadow(
                            color = Color.White
                        )
                    ),
                    onDateChangeListener = { selectedDate ->
                        birthDateInput = selectedDate.toString()
                    },
                    hideHeader = true
                )}

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "We use this information to calculate and provide you with \n daily personalized recommendations",
                    color = Color(0xFF616161),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(onClick = {
                    viewModel.updateBirthDate(birthDateInput)
                    onNext()
                },
                    text = "NEXT",
                    textColor = Color.Black,
                    textSize = 18.sp
                )
            }
        }
    }
