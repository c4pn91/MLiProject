package com.c4pn91.mliproject.domain.state

sealed class DetailsResultState<T> {
    data class Success<T>(val details: T) : DetailsResultState<T>()
    object     Empty                      : DetailsResultState<Nothing>()
    data class Error(val message: String) : DetailsResultState<Nothing>()
}


