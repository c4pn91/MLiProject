package com.c4pn91.mliproject.data.service

import com.c4pn91.mliproject.common.util.Constants
import com.c4pn91.mliproject.data.api.ApiClient
import com.c4pn91.mliproject.data.datasource.remote.RemoteProductDataSource
import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.data.model.SearchResultDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteProductDataSourceTest {

    private val apiClient: ApiClient = mockk<ApiClient>()
    private lateinit var remoteProductDataSource: RemoteProductDataSource

    val QUERY = "balón"
    val PRODUCT_ID = "1234"

    @Before
    fun setUp() {
        remoteProductDataSource = RemoteProductDataSource(apiClient)
    }

    @Test
    fun testFetchProducts_Success(): Unit = runBlocking {
        // Given
        val expectedResponse : SearchResultDTO = mockk()
        val mockResponse = Response.success(expectedResponse)

        coEvery { apiClient.getProducts(any(), any(), any(), any()) } returns mockResponse

        // When
        val result = remoteProductDataSource.fetchProducts(QUERY, 0, 10)


        // Then
        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
        assertEquals(result.getOrNull(), expectedResponse)
    }

    @Test
    fun testFetchProducts_Failure() = runBlocking {
        // Given
        val errorJson = """
        {
            "message": "Resource not found.",
            "error": "not_found",
            "status": 404,
            "cause": []
        }
        """
        val mockResponse = Response.error<SearchResultDTO>(
            404,
            ResponseBody.create("application/json".toMediaType(), errorJson)
        )
        coEvery { apiClient.getProducts(any(), any(), any(), any()) } returns mockResponse

        // When
        val result = remoteProductDataSource.fetchProducts(QUERY, 0, 10)
        val errorMessage = result.exceptionOrNull()?.message

        // Then
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
        assertTrue(errorMessage!!.contains("not_found"))
    }

    @Test()
    fun testFetchProducts_Throwable() = runBlocking {
        // Given
        coEvery { apiClient.getProducts(Constants.SITE_ID, QUERY, 0, 10) }
            .throws(RuntimeException("Network Error"))

        // When
        val result = remoteProductDataSource.fetchProducts(QUERY, 0, 10)

        // Then
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
        assertEquals(result.exceptionOrNull()?.message, "Network Error")
    }

    @Test
    fun testFetchDetails_Success() = runBlocking {
        // Given
        val expectedResponse : DetailsResponseDTO = mockk()
        val mockResponse = Response.success(expectedResponse)

        coEvery { apiClient.getProductById(PRODUCT_ID) } returns mockResponse

        // When
        val result = remoteProductDataSource.fetchDetails(PRODUCT_ID)

        // Then
        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
        assertEquals(expectedResponse, result.getOrNull())
    }

    @Test
    fun testFetchDetails_ErrorResponse() = runBlocking {
        // Given
        val errorJson = """
        {
            "message": "Resource not found.",
            "error": "not_found",
            "status": 404,
            "cause": []
        }
        """
        val mockResponse = Response.error<DetailsResponseDTO>(
            404,
            ResponseBody.create("application/json".toMediaType(), errorJson)
        )

        coEvery { apiClient.getProductById(PRODUCT_ID) } returns mockResponse

        // When
        val result = remoteProductDataSource.fetchDetails(PRODUCT_ID)
        val errorMessage = result.exceptionOrNull()?.message

        // Then
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
        assertTrue(errorMessage!!.contains("not_found"))
    }

    @Test
    fun testFetchDetails_Throwable() = runBlocking {
        // Given
        coEvery { apiClient.getProductById(PRODUCT_ID) } throws RuntimeException("Network Error")

        // When
        val result = remoteProductDataSource.fetchDetails(PRODUCT_ID)

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message == "Network Error")
    }
}