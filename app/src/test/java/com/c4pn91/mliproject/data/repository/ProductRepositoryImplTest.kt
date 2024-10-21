package com.c4pn91.mliproject.data.repository

import com.c4pn91.mliproject.data.model.PagingDTO
import com.c4pn91.mliproject.data.model.ProductResultDTO
import com.c4pn91.mliproject.data.model.SearchResultDTO
import com.c4pn91.mliproject.data.datasource.remote.RemoteProductDataSource
import com.c4pn91.mliproject.domain.state.ProductsResultState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductRepositoryImplTest {

    private val remoteProductDataSource: RemoteProductDataSource = mockk()
    private lateinit var repository: ProductRepositoryImpl

    val QUERY = "balón"

    @Before
    fun setUp() {
        repository = ProductRepositoryImpl(remoteProductDataSource)
    }

    @Test
    fun testGetProductsFromApi_Success() = runBlocking {
        // Given
        val expectedResponse = SearchResultDTO(
            pagingDTO = PagingDTO(total = 100, limit = 10, primaryResults = 10, offset = 0),
            productResultDTO = listOf(
                ProductResultDTO(id = "1", title = "Producto 1"),
                ProductResultDTO(id = "2", title = "Producto 2")
            )
        )

        coEvery { remoteProductDataSource.fetchProducts(QUERY, 0, 10) } returns Result.success(expectedResponse)

        // When
        val result = repository.getProductsFromApi(QUERY)

        // Then
        assert(result is ProductsResultState.Success)
        assertNotNull((result as ProductsResultState.Success).products)
        assertEquals(2, result.products.size)
    }

    @Test
    fun testGetProductsFromApi_Empty() = runBlocking {
        // Given
        val expectedResponse = SearchResultDTO(
            pagingDTO = PagingDTO(total = 100, limit = 10, primaryResults = 0, offset = 0),
            productResultDTO = listOf()
        )

        coEvery { remoteProductDataSource.fetchProducts(QUERY, 0, 10) } returns Result.success(expectedResponse)

        // When
        val result = repository.getProductsFromApi(QUERY)

        // Then
        assert(result is ProductsResultState.Empty)
    }

    @Test
    fun testGetProductsFromApi_Error() = runBlocking {
        // Given
        val errorMessage = "Error de conexión"
        coEvery { remoteProductDataSource.fetchProducts(QUERY, 0, 10) } returns Result.failure(Exception(errorMessage))

        // When
        val result = repository.getProductsFromApi(QUERY)

        // Then
        assert(result is ProductsResultState.Error)
        assertEquals(errorMessage, (result as ProductsResultState.Error).message)
    }

}