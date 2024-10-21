package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.c4pn91.mliproject.presentation.theme.BlueMeli

@Composable
fun ImageCarousel(
    imageUrls: List<String>,
    carouselState: MutableState<Int>
) {
    val listState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val imageHeight = screenHeight * 0.45f // 45% del alto de la screen
    val imageWidth = screenWidth * 1.01f // 101% del ancho dela screen

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        LazyRow(state = listState, modifier = Modifier.fillMaxHeight()) {
            items(imageUrls.size) { index ->
                Image(
                    painter = rememberImagePainter(
                        data = imageUrls[index],
                        builder = {
                            scale(Scale.FILL)
                        }
                    ),
                    contentDescription = "Imagen del Carrusel",
                    modifier = Modifier
                        .width(imageWidth)
                        .fillMaxHeight(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }

        // Actualizar el estado del carrusel conforme el usuario se desplace
        LaunchedEffect(listState.firstVisibleItemIndex) {
            carouselState.value = listState.firstVisibleItemIndex
        }

        // Badge en la esquina superior izquierda
        Text(
            fontSize = 14.sp,
            text = "${carouselState.value + 1}/${imageUrls.size}",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp, bottom = 16.dp)
                .background(Color.Gray.copy(alpha = 0.8f), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            color = Color.White
        )
    }

    // Indicadores de puntos debajo del carrusel
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        imageUrls.forEachIndexed { index, _ ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(if (index == carouselState.value) BlueMeli else Color.LightGray)
            )
        }
    }
}