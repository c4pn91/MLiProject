package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ProductFeatures(features: ArrayList<Map<String, String>>) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(0.dp)) {
            features.forEachIndexed { index, feature ->
                val backgroundColor = if (index % 2 == 0) Color.Transparent
                else Color.LightGray.copy(alpha = 0.3f)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                ) {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        text = feature.get("name") ?: "",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = feature.get("valueName") ?: "",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}