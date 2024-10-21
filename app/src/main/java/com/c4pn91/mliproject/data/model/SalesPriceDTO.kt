package com.c4pn91.mliproject.data.model

import com.google.gson.annotations.SerializedName

data class SalePriceDTO(
    @SerializedName("amount"         ) var amount        : Double?           = null,
    @SerializedName("currency_id"    ) var currencyId    : String?           = null,
    @SerializedName("regular_amount" ) var regularAmount : Double?           = null,
    @SerializedName("type"           ) var type          : String?           = null
)