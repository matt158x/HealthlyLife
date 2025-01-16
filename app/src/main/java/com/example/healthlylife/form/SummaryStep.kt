package com.example.healthlylife.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.data.UserFormData
import com.example.healthlylife.presentation.damionFontFamily

@Composable
fun SummaryStep(modifier: Modifier = Modifier, navController: NavController, userFormData: UserFormData, onSubmit: () -> Unit, onBack: () -> Unit) {



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
                    text = "Your projected progress",
                    fontFamily = damionFontFamily,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(30.dp))

                // Sekcja wykresu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp) // Dopasuj wysokość do obrazu
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Wykres
                    Image(
                        painter = painterResource(id = userFormData.chartImage),
                        contentDescription = "Goal Chart",
                        modifier = Modifier.fillMaxHeight()
                    )

                    // Waga na środku po lewej
                    Text(
                        text = "${userFormData.currentWeight.toInt()} kg",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 10.dp)
                    )

                    // Waga docelowa na środku po prawej
                    Text(
                        text = "${userFormData.targetWeight.toInt()} kg",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 10.dp)
                    )


                    Text(
                        text = "",
                        fontFamily = damionFontFamily,
                        color = Color.White,
                        fontSize = 22.sp
                    )

                    Text(
                        text = "",
                        fontFamily = damionFontFamily,
                        color = Color.White,
                        fontSize = 22.sp
                    )

                }

                Spacer(modifier = Modifier.height(50.dp))

                // Dodatkowy tekst
                Text(
                    text = "We use this information to calculate and provide you with \n daily personalized recommendations",
                    color = Color(0xFF616161),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    onClick = {
                        onSubmit()
                    },
                    text = "GET STARTED",
                    textColor = Color.Black,
                    textSize = 18.sp
                )
            }
        }
    }
}