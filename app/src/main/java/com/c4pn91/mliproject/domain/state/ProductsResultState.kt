package com.c4pn91.mliproject.domain.state

sealed class ProductsResultState<T> {
    data class Success<T>(val products: List<T>, val hasMore: Boolean) : ProductsResultState<T>()
    object     Empty                                                   : ProductsResultState<Nothing>()
    data class Error(val message: String)                              : ProductsResultState<Nothing>()
}