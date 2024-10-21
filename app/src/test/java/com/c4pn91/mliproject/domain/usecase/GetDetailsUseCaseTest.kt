package com.c4pn91.mliproject.domain.usecase

import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.data.model.toDetailsResponseUI
import com.c4pn91.mliproject.domain.repository.DetailsRepository
import com.c4pn91.mliproject.domain.repository.ProductsRepository
import com.c4pn91.mliproject.domain.state.DetailsResultState
import com.c4pn91.mliproject.presentation.state.DetailsResultStateUI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetDetailsUseCaseTest {

    private val repository: DetailsRepository = mockk()
    private lateinit var getDetailsUseCase: GetDetailsUseCase

    val PRODUCT_ID = "1234"

    @Before
    fun setUp() {
        getDetailsUseCase = GetDetailsUseCase(repository)
    }

    @Test
    fun testInvoke_Success() = runBlocking {
        // Given
        val expectedDetails = DetailsResponseDTO("1", "Title")

        coEvery { repository.getDetailsFromApi(PRODUCT_ID) } returns DetailsResultState.Success(expectedDetails)

        // When
        val result = getDetailsUseCase(PRODUCT_ID)

        // Then
        assert(result is DetailsResultStateUI.Success)
        assertNotNull((result as DetailsResultStateUI.Success).details)
        assertEquals(expectedDetails.toDetailsResponseUI(), result.details)
    }

    @Test
    fun testInvoke_Empty() = runBlocking {
        // Given
        coEvery { repository.getDetailsFromApi(PRODUCT_ID) } returns DetailsResultState.Empty

        // When
        val result = getDetailsUseCase(PRODUCT_ID)

        // Then
        assert(result is DetailsResultStateUI.Empty)
    }

    @Test
    fun testInvoke_Error() = runBlocking {
        // Given
        val errorMessage = "Resource not found"
        coEvery { repository.getDetailsFromApi(PRODUCT_ID) } returns DetailsResultState.Error(errorMessage)

        // When
        val result = getDetailsUseCase(PRODUCT_ID)

        // Then
        assert(result is DetailsResultStateUI.Error)
        assertEquals(errorMessage, (result as DetailsResultStateUI.Error).message)
    }
}