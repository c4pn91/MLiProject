package com.c4pn91.mliproject.data.repository

import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.data.datasource.remote.RemoteProductDataSource
import com.c4pn91.mliproject.domain.state.DetailsResultState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailsRepositoryImplTest {

    private val remoteProductDataSource: RemoteProductDataSource = mockk()
    private lateinit var repository: DetailsRepositoryImpl

    val PRODUCT_ID = "1234"

    @Before
    fun setUp() {
        repository = DetailsRepositoryImpl(remoteProductDataSource)
    }

    @Test
    fun testGetDetailsFromApi_Success() = runBlocking {
        // Given
        val expectedDetails : DetailsResponseDTO = mockk()

        coEvery { remoteProductDataSource.fetchDetails(PRODUCT_ID) } returns Result.success(expectedDetails)

        // When
        val result = repository.getDetailsFromApi(PRODUCT_ID)

        // Then
        assert(result is DetailsResultState.Success)
        assertNotNull((result as DetailsResultState.Success).details)
        assertEquals(expectedDetails, result.details)
    }

    @Test
    fun testGetDetailsFromApi_ErrorResponse() = runBlocking {
        // Given
        val errorMessage = "Resource not found"
        coEvery { remoteProductDataSource.fetchDetails(PRODUCT_ID) } returns Result.failure(Exception(errorMessage))

        // When
        val result = repository.getDetailsFromApi(PRODUCT_ID)

        // Then
        assert(result is DetailsResultState.Error)
        assertEquals(errorMessage, (result as DetailsResultState.Error).message)
    }

    @Test(expected = Exception::class)
    fun testGetDetailsFromApi_NetworkError() = runBlocking {
        // Given
        coEvery { remoteProductDataSource.fetchDetails(PRODUCT_ID) }
            .throws(RuntimeException("Network Error"))

        // When
        val result = repository.getDetailsFromApi(PRODUCT_ID)

        // Then
        assert(result is DetailsResultState.Error)
        assertEquals("Network Error", (result as DetailsResultState.Error).message)
    }
}