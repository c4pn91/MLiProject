package com.c4pn91.mliproject.data.model

import com.c4pn91.mliproject.common.util.Utilities
import com.c4pn91.mliproject.presentation.model.ProductResultUI
import com.google.gson.annotations.SerializedName

data class ProductResultDTO(
    @SerializedName("id"                  ) var id                 : String?       = null,
    @SerializedName("title"               ) var title              : String?       = null,
    @SerializedName("condition"           ) var condition          : String?       = null,
    @SerializedName("permalink"           ) var permalink          : String?       = null,
    @SerializedName("site_id"             ) var siteId             : String?       = null,
    @SerializedName("domain_id"           ) var domainId           : String?       = null,
    @SerializedName("thumbnail"           ) var thumbnail          : String?       = null,
    @SerializedName("currency_id"         ) var currencyId         : String?       = null,
    @SerializedName("price"               ) var price              : Double?       = null,
    @SerializedName("original_price"      ) var originalPrice      : Double?       = null,
    @SerializedName("sale_price"          ) var salePriceDTO       : SalePriceDTO? = SalePriceDTO(),
    @SerializedName("available_quantity"  ) var availableQuantity  : Int?          = null,
    @SerializedName("accepts_mercadopago" ) var acceptsMercadopago : Boolean?      = null,
)

fun ProductResultDTO.toProductResultUI() = ProductResultUI(
    id = id,
    title = title,
    condition = if (condition == "used") "Producto usado" else "Producto nuevo",
    thumbnail = thumbnail,
    price = Utilities.formatFloatToCurrency(price),
    originalPrice = Utilities.formatFloatToCurrency(originalPrice),
    availableQuantity = "Quedan ${availableQuantity.toString()} unidades",
    acceptsMercadopago = if (acceptsMercadopago == true) "Acepta Mercado Pago" else "No acepta Mercado Pago",
)