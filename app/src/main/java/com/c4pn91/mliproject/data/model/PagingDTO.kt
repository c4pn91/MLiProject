package com.c4pn91.mliproject.data.model

import com.google.gson.annotations.SerializedName

data class PagingDTO(
    @SerializedName("total"           ) var total          : Int? = null,
    @SerializedName("primary_results" ) var primaryResults : Int? = null,
    @SerializedName("offset"          ) var offset         : Int? = null,
    @SerializedName("limit"           ) var limit          : Int? = null
)