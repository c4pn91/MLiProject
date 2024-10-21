package com.c4pn91.mliproject.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c4pn91.mliproject.domain.usecase.GetProductsUseCase
import com.c4pn91.mliproject.presentation.model.ProductResultUI
import com.c4pn91.mliproject.presentation.state.ProductsResultStateUI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getProductsUseCase : GetProductsUseCase = mockk()
    private lateinit var productsViewModel: ProductsViewModel

    val QUERY = "balón"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)

        productsViewModel = ProductsViewModel(getProductsUseCase)
    }

    @Test
    fun testLoadProducts_Success() = runBlocking {
        // Given
        val expectedProducts = listOf(ProductResultUI(id = "1", title = "Producto 1"))
        val result = ProductsResultStateUI.Success(expectedProducts, true)

        coEvery { getProductsUseCase(QUERY) } returns result

        // When
        productsViewModel.loadProducts(QUERY)

        // then
        assertTrue(productsViewModel.productsState.value is ProductsResultStateUI.Success)
        val products = (productsViewModel.productsState.value as ProductsResultStateUI.Success).products
        assertEquals(expectedProducts, products)
    }

    @Test
    fun testLoadProducts_Empty() = runBlocking {
        // Given
        val result = ProductsResultStateUI.Empty
        coEvery { getProductsUseCase(QUERY) } returns result

        // When
        productsViewModel.loadProducts(QUERY)

        // Then
        assertTrue(productsViewModel.productsState.value is ProductsResultStateUI.Empty)
    }

    @Test
    fun testLoadProducts_Error() = runBlocking {
        // Given
        val errorMessage = "Error de conexión"
        val result = ProductsResultStateUI.Error(errorMessage)

        coEvery { getProductsUseCase(QUERY) } returns result

        // When
        productsViewModel.loadProducts(QUERY)

        // Then
        assertTrue(productsViewModel.productsState.value is ProductsResultStateUI.Error)
        assertEquals(errorMessage, (productsViewModel.productsState.value as ProductsResultStateUI.Error).message)
    }

    @Test
    fun testLoadProducts_NoMoreResults() = runBlocking {
        // Given
        val expectedProducts = listOf(ProductResultUI(id = "1", title = "Producto 1"))
        val result = ProductsResultStateUI.Success(expectedProducts, false) // No hay más resultados

        coEvery { getProductsUseCase(QUERY) } returns result

        // When
        productsViewModel.loadProducts(QUERY)

        // Then
        assertTrue(productsViewModel.productsState.value is ProductsResultStateUI.Success)
        assertEquals(expectedProducts, (productsViewModel.productsState.value as ProductsResultStateUI.Success).products)
        assertFalse(productsViewModel.hasMoreResults())
    }
}