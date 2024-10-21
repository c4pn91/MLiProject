package com.c4pn91.mliproject.domain.usecase

import com.c4pn91.mliproject.data.model.ProductResultDTO
import com.c4pn91.mliproject.data.model.toProductResultUI
import com.c4pn91.mliproject.data.repository.ProductRepositoryImpl
import com.c4pn91.mliproject.domain.repository.ProductsRepository
import com.c4pn91.mliproject.domain.state.ProductsResultState
import com.c4pn91.mliproject.presentation.state.ProductsResultStateUI
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(query: String): ProductsResultStateUI {
        val result : ProductsResultState<out ProductResultDTO> = repository.getProductsFromApi(query)
        return when (result) {
            is ProductsResultState.Success -> {
                ProductsResultStateUI.Success(result.products.map { it.toProductResultUI() }, result.hasMore)
            }
            is ProductsResultState.Empty -> {
                ProductsResultStateUI.Empty
            }
            is ProductsResultState.Error -> {
                ProductsResultStateUI.Error(result.message)
            }
        }
    }
}