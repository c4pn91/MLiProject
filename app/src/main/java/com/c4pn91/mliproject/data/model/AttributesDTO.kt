package com.c4pn91.mliproject.data.model

import com.google.gson.annotations.SerializedName

data class AttributesDTO(
    @SerializedName("name"       ) var name      : String?  = null,
    @SerializedName("value_name" ) var valueName : String?  = null,
    @SerializedName("value_type" ) var valueType : String?  = null
)
