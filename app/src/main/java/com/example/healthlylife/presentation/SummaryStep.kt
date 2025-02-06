package com.example.healthlylife.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.viewmodel.SummaryStepViewModel

@Composable
fun SummaryStep(
    viewModel: SummaryStepViewModel = hiltViewModel(),
    onSubmit: () -> Unit,
    onBack: () -> Unit
) {
    val userFormData by viewModel.userFormData

    if (userFormData == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...", color = Color.White)
        }
        return
    }

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
                text = "Your projected progress",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .width(350.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = userFormData!!.chartImage),
                    contentDescription = "Goal Chart",
                    modifier = Modifier.fillMaxHeight()
                )

                Text(
                    text = "${userFormData!!.currentWeight} kg",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(10.dp)
                )

                Text(
                    text = if (userFormData!!.goal == "Maintain Weight") {
                        "${userFormData!!.currentWeight} kg"
                    } else {
                        "${userFormData!!.targetWeight} kg"
                    },
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(10.dp)
                )

                Text(
                    text = "Today",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                )

                Text(
                    text = userFormData!!.goalData,
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "0.5 kg / week",
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Recommend calorie intake per day:",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${userFormData!!.bmr} kcal",
                color = Color(0XFFBADA55),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = "We recommend this weight pace for long-term success",
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

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