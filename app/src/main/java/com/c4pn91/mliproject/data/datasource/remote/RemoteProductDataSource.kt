package com.c4pn91.mliproject.data.datasource.remote

import com.c4pn91.mliproject.common.util.Constants
import com.c4pn91.mliproject.data.api.ApiClient
import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.data.model.SearchResultDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteProductDataSource @Inject constructor(
    private val apiClient: ApiClient
) {

    @Throws(Exception::class)
    suspend fun fetchProducts(searchText: String, offset: Int, limit: Int): Result<SearchResultDTO> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<SearchResultDTO>? = apiClient
                    .getProducts(siteId = Constants.SITE_ID, query = searchText, offset = offset, limit = limit)
                if (response?.isSuccessful == true) {
                    Result.success(response.body() ?: SearchResultDTO())
                } else {
                    val errorMessage = response?.errorBody()?.string() ?: "Error desconocido"
                    Result.failure(Exception(errorMessage))
                }
            } catch (error: Exception) {
                Result.failure(error)
            }
        }
    }

    @Throws(Exception::class)
    suspend fun fetchDetails(productId: String): Result<DetailsResponseDTO> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<DetailsResponseDTO>? = apiClient.getProductById(productId)!!
                if (response?.isSuccessful == true) {
                    Result.success(response?.body() ?: DetailsResponseDTO())
                } else {
                    val errorMessage = response?.errorBody()?.string() ?: "Error desconocido"
                    Result.failure(Exception(errorMessage))
                }
            } catch (error: Exception) {
                Result.failure(error)
            }
        }
    }

}