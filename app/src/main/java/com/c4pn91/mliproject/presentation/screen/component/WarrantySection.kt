package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WarrantySection(warranty: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.3f))
    ) {
        Icon(
            Icons.Filled.Info,
            contentDescription = "Info",
            modifier = Modifier.padding(12.dp),
            tint = Color.Gray
        )
        Text(
            text = warranty,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(top = 12.dp, end = 12.dp, bottom = 12.dp)
                .align(Alignment.CenterVertically),
        )
    }

}