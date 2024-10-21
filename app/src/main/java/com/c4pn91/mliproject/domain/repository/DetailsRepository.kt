package com.c4pn91.mliproject.domain.repository

import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.domain.state.DetailsResultState

interface DetailsRepository {
    suspend fun getDetailsFromApi(productId: String) : DetailsResultState<out DetailsResponseDTO>
}