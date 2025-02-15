package com.example.healthlylife.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFF32CD32),
    textColor: Color = Color.White,
    cornerRadius: Dp = 10.dp,
    enabled: Boolean = true,
    textSize: TextUnit = 12.sp
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(40.dp)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = textSize
        )
    }
}

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

@Composable
fun CustomAddButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFF292929),
    textColor: Color = Color.White,
    cornerRadius: Dp = 20.dp,
    enabled: Boolean = true,
    textSize: TextUnit = 24.sp
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(60.dp)
            .width(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = textSize
        )
    }
}

@Composable
fun CustomButtonHome(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFF292929),
    textColor: Color = Color.White,
    cornerRadius: Dp = 10.dp,
    enabled: Boolean = true,
    textSize: TextUnit = 18.sp,
    image: Painter,
    textAlign: TextAlign = TextAlign.Start
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(cornerRadius)
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = image,
                contentDescription = null,
                Modifier.padding(start=15.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = text,
                color = textColor,
                modifier = Modifier.weight(1f),
                fontSize = textSize,
                textAlign = textAlign
            )
        }
    }
}

