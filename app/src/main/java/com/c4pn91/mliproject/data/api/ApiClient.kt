package com.c4pn91.mliproject.data.api

import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.data.model.SearchResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("sites/{site_id}/search")
    suspend fun getProducts(
        @Path("site_id") siteId: String,
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<SearchResultDTO>?

    @GET("items/{id}")
    suspend fun getProductById(
        @Path("id") productId: String
    ): Response<DetailsResponseDTO>?

}