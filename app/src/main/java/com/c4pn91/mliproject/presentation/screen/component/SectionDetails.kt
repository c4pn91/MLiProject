package com.c4pn91.mliproject.presentation.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.c4pn91.mliproject.presentation.model.DetailsResponseUI

@Composable
fun SectionsDetails(
    productDetail: DetailsResponseUI,
) {
    val scrollState = rememberScrollState()
    val carouselState = remember { mutableStateOf(1) }

    return Column(
        modifier = Modifier
            .verticalScroll(scrollState)) {

        if (!productDetail.condition.isNullOrEmpty())
            ConditionText(condition = "${productDetail.condition}")

        if (!productDetail.title.isNullOrEmpty())
            DetailTitle("${productDetail.title}")

        ImageCarousel(imageUrls = productDetail.pictures, carouselState = carouselState)

        Spacer(modifier = Modifier.height(16.dp))

        DetailPrice(detailPrice = "${productDetail.price}")

        if (!productDetail.warranty.isNullOrEmpty())
            WarrantySection(warranty = "${productDetail.warranty}")

        if (productDetail.attributes.isNotEmpty()) {
            ProductFeaturesTitle()
            ProductFeatures(features = productDetail.attributes)
        }
    }
}

