package com.c4pn91.mliproject.data.repository

import com.c4pn91.mliproject.data.model.ProductResultDTO
import com.c4pn91.mliproject.data.model.SearchResultDTO
import com.c4pn91.mliproject.data.datasource.remote.RemoteProductDataSource
import com.c4pn91.mliproject.domain.repository.ProductsRepository
import com.c4pn91.mliproject.domain.state.ProductsResultState
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteProductDataSource: RemoteProductDataSource
) : ProductsRepository {

    private var currentOffset = 0
    private val limit = 10
    private var totalResults = 0

    override suspend fun getProductsFromApi(query: String): ProductsResultState<out ProductResultDTO> {
        val result: Result<SearchResultDTO> =
            remoteProductDataSource.fetchProducts(query, currentOffset, limit)

        return result.fold(
            onSuccess = { searchResult ->
                totalResults = searchResult.pagingDTO?.total ?: 0
                val products = searchResult.productResultDTO

                if (products.isNotEmpty()) {
                    currentOffset += products.size
                    val hasMore = totalResults > currentOffset

                    // Devolver productos y si hay más por consultar
                    ProductsResultState.Success(products, hasMore)
                } else {
                    ProductsResultState.Empty
                }
            },
            onFailure = { exception ->
                ProductsResultState.Error(exception.message ?: "Error desconocido ha ocurrido")
            }
        )
    }

}