package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.c4pn91.mliproject.common.navigation.Routes
import com.c4pn91.mliproject.presentation.model.ProductResultUI

@Composable
fun ProductItem(product: ProductResultUI, navController: NavController) {
    Row(modifier = Modifier
        .clickable {
            navController.navigate("${Routes.DetailsScreen.route}/${product.id}")
        }
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp)) {

        Card(
            modifier = Modifier
                .width(130.dp)
                .height(130.dp)
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.Top),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            if (product.thumbnail?.isEmpty() == false)
                ProductImage("${product.thumbnail}")
        }

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(start = 8.dp)) {
                TitleProductItem(title = "${product.title}")

                OriginalPrice(oPrice = "${product.originalPrice}")

                Price(price = "${product.price}")

                AcceptMP("${product.acceptsMercadopago}")
        }
    }
}