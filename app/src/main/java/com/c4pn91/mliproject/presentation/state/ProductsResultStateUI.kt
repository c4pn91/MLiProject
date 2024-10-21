package com.c4pn91.mliproject.presentation.state

import com.c4pn91.mliproject.presentation.model.ProductResultUI

sealed class ProductsResultStateUI {
    object     Init                                                               : ProductsResultStateUI()
    object     Loading                                                            : ProductsResultStateUI()
    data class Success(val products: List<ProductResultUI>, val hasMore: Boolean) : ProductsResultStateUI()
    object     Empty                                                              : ProductsResultStateUI()
    data class Error(val message: String)                                         : ProductsResultStateUI()
}