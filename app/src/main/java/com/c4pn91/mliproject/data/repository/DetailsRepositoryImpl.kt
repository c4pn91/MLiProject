package com.c4pn91.mliproject.data.repository

import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.data.datasource.remote.RemoteProductDataSource
import com.c4pn91.mliproject.domain.repository.DetailsRepository
import com.c4pn91.mliproject.domain.state.DetailsResultState
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val remoteProductDataSource: RemoteProductDataSource
) : DetailsRepository {

    override suspend fun getDetailsFromApi(productId: String): DetailsResultState<out DetailsResponseDTO> {
        val result = remoteProductDataSource.fetchDetails(productId)

        return result.fold(
            onSuccess = { detailsResponse ->
                DetailsResultState.Success(detailsResponse)
            },
            onFailure = { exception ->
                DetailsResultState.Error(exception.message ?: "Error desconocido")
            }
        )
    }

}