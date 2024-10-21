package com.c4pn91.mliproject.presentation.model

data class PagingUI(
    var total          : Int? = null,
    var primaryResults : Int? = null,
    var offset         : Int? = null,
    var limit          : Int? = null
)