package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConditionText(condition: String) {
    Text(
        text = condition,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 12.dp, start = 12.dp),
        color = Color.Gray)
}