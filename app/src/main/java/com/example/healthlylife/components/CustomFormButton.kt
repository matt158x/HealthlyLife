package com.example.healthlylife.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomFormButton(
    text: String,
    isClicked: Boolean,
    clickedColor: Color = Color(0xFF25BA3A),
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFF292929),
    textColor: Color = Color(0xFF616161),
    enabled: Boolean = true,
    textSize: TextUnit = 18.sp,
    buttonHeight: Dp = 110.dp
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(buttonHeight)
            .padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = if (isClicked) clickedColor else containerColor),
        shape = RectangleShape

    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = textSize
        )
    }
}
