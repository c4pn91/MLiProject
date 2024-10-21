package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun OriginalPrice(oPrice: String) {
    Text(
        text = oPrice,
        fontWeight = FontWeight.Normal,
        style = TextStyle(
            textDecoration = TextDecoration.LineThrough
        ),
        color = Color.Gray,
        fontSize = 14.sp
    )
}