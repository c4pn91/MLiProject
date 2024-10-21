package com.c4pn91.mliproject.domain.repository

import com.c4pn91.mliproject.data.model.ProductResultDTO
import com.c4pn91.mliproject.domain.state.ProductsResultState

interface ProductsRepository {
    suspend fun getProductsFromApi(query: String) : ProductsResultState<out ProductResultDTO>
}