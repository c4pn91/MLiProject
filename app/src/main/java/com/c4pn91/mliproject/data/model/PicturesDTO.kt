package com.c4pn91.mliproject.data.model

import com.google.gson.annotations.SerializedName

data class PicturesDTO(
    @SerializedName("id"         ) var id        : String? = null,
    @SerializedName("url"        ) var url       : String? = null,
    @SerializedName("secure_url" ) var secureUrl : String? = null,
)
