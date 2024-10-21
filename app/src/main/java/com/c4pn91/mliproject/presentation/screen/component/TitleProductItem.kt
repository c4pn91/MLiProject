package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun TitleProductItem(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        maxLines = 3,
        minLines = 3,
        overflow = TextOverflow.Ellipsis,
        fontSize = 16.sp,
        style = TextStyle(
            letterSpacing = (-0.02).em
        ),
        modifier = Modifier.padding(bottom = 6.dp)
    )
}