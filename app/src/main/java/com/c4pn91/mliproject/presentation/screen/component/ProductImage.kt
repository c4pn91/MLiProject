package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import coil.size.Scale

@Composable
fun ProductImage(thumbnail: String) {
    Image(
        painter = rememberImagePainter(
            data = thumbnail,
            builder = {
                scale(Scale.FIT)
            }),
        contentDescription = "Imagen del producto",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}