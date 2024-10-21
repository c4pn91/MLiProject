package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.c4pn91.mliproject.presentation.theme.BlueMeli

@Composable
fun LinearProgress() {
    Box {
        LinearProgressIndicator(
            color = BlueMeli,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
        )
    }
}