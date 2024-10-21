package com.c4pn91.mliproject.domain.usecase

import com.c4pn91.mliproject.data.model.DetailsResponseDTO
import com.c4pn91.mliproject.data.model.toDetailsResponseUI
import com.c4pn91.mliproject.domain.repository.DetailsRepository
import com.c4pn91.mliproject.domain.state.DetailsResultState
import com.c4pn91.mliproject.presentation.state.DetailsResultStateUI
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: DetailsRepository
) {

    suspend operator fun invoke(productId: String): DetailsResultStateUI {
        val result : DetailsResultState<out DetailsResponseDTO> = repository.getDetailsFromApi(productId)
        return when (result) {
            is DetailsResultState.Success -> {
                DetailsResultStateUI.Success(result.details.toDetailsResponseUI())
            }
            is DetailsResultState.Empty -> {
                DetailsResultStateUI.Empty
            }
            is DetailsResultState.Error -> {
                DetailsResultStateUI.Error(result.message)
            }
        }
    }

}