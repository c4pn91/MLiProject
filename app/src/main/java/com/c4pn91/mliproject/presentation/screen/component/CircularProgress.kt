package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.c4pn91.mliproject.presentation.theme.BlueMeli

@Composable
fun CircularProgress() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        CircularProgressIndicator(
            color = BlueMeli,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }
}