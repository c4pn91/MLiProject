package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Price(price: String) {
    Text(
        text = price,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    )
}