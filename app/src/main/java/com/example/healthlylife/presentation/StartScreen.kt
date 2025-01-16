package com.example.healthlylife.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healthlylife.R
import com.example.healthlylife.components.CustomButton

val submarinerFontFamily = FontFamily(
    Font(R.font.submariner_black_italic, weight = FontWeight.Normal)
)
val damionFontFamily = FontFamily(
    Font(R.font.damion,weight = FontWeight.Normal)
)

@Composable
fun StartScreen(navController: NavController) {

        Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Healthy",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontFamily = submarinerFontFamily
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Get expert advice",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontFamily = damionFontFamily
                )


                Text(
                    text = "on what, when and how much to eat,\n tailored to suit your individual\n lifestyle",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontFamily = damionFontFamily
                )

                Spacer(modifier = Modifier.height(60.dp))

                CustomButton(
                    text = "GET STARTED",
                    onClick = { navController.navigate("signup") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    buildAnnotatedString {
                        append("Don't have an account? ")

                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF32CD32),
                                fontWeight = FontWeight.Light
                            )
                        ) {
                            append("Log in")
                        }
                    },
                    style = TextStyle(fontSize = 14.sp, color = Color.White),
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }

