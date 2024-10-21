package com.c4pn91.mliproject.presentation.state

import com.c4pn91.mliproject.presentation.model.DetailsResponseUI

sealed class DetailsResultStateUI {
    object     Init                                    : DetailsResultStateUI()
    object     Loading                                 : DetailsResultStateUI()
    data class Success(val details: DetailsResponseUI) : DetailsResultStateUI()
    object     Empty                                   : DetailsResultStateUI()
    data class Error(val message: String)              : DetailsResultStateUI()
}