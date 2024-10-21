package com.c4pn91.mliproject.data.model

import com.google.gson.annotations.SerializedName

data class SearchResultDTO(
    @SerializedName("site_id" ) var siteId           : String?                = null,
    @SerializedName("query"   ) var query            : String?                = null,
    @SerializedName("paging"  ) var pagingDTO        : PagingDTO?             = PagingDTO(),
    @SerializedName("results" ) var productResultDTO : List<ProductResultDTO> = emptyList()
)