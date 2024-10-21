package com.c4pn91.mliproject.domain.usecase

import com.c4pn91.mliproject.data.model.ProductResultDTO
import com.c4pn91.mliproject.data.model.toProductResultUI
import com.c4pn91.mliproject.domain.repository.ProductsRepository
import com.c4pn91.mliproject.domain.state.ProductsResultState
import com.c4pn91.mliproject.presentation.state.ProductsResultStateUI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetProductsUseCaseTest {

    private val repository: ProductsRepository = mockk()
    private lateinit var getProductsUseCase: GetProductsUseCase

    val QUERY = "balón"

    @Before
    fun setUp() {
        getProductsUseCase = GetProductsUseCase(repository)
    }

    @Test
    fun testInvoke_Success() = runBlocking {
        // Given
        val expectedProducts = listOf(ProductResultDTO("1", "Producto 1"))

        coEvery { repository.getProductsFromApi(QUERY) } returns ProductsResultState.Success(expectedProducts, true)

        // When
        val result = getProductsUseCase(QUERY)

        // Then
        assert(result is ProductsResultStateUI.Success)
        assertEquals(expectedProducts.map { it.toProductResultUI() }, (result as ProductsResultStateUI.Success).products)
        assertTrue(result.hasMore)
    }

    @Test
    fun testInvoke_Empty() = runBlocking {
        // Given
        coEvery { repository.getProductsFromApi(QUERY) } returns ProductsResultState.Empty

        // Then
        val result = getProductsUseCase(QUERY)

        // When
        assert(result is ProductsResultStateUI.Empty)
    }

    @Test
    fun testInvoke_Error() = runBlocking {
        // Given
        val errorMessage = "Error de conexión"
        coEvery { repository.getProductsFromApi(QUERY) } returns ProductsResultState.Error(errorMessage)

        // When
        val result = getProductsUseCase(QUERY)

        // Then
        assert(result is ProductsResultStateUI.Error)
        assertEquals(errorMessage, (result as ProductsResultStateUI.Error).message)
    }
}