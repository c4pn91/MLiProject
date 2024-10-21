package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailPrice(detailPrice: String) {
    Text(
        text = detailPrice,
        style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.SemiBold),
        modifier = Modifier.padding(bottom = 16.dp, start = 20.dp)
    )
}