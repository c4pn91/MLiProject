package com.c4pn91.mliproject.data.model

import com.c4pn91.mliproject.common.util.Utilities
import com.c4pn91.mliproject.presentation.model.DetailsResponseUI
import com.google.gson.annotations.SerializedName

data class DetailsResponseDTO(
    @SerializedName("id"               ) var id              : String?                  = null,
    @SerializedName("title"            ) var title           : String?                  = null,
    @SerializedName("price"            ) var price           : Double?                     = null,
    @SerializedName("base_price"       ) var basePrice       : Double?                     = null,
    @SerializedName("currency_id"      ) var currencyId      : String?                  = null,
    @SerializedName("initial_quantity" ) var initialQuantity : Int?                     = null,
    @SerializedName("condition"        ) var condition       : String?                  = null,
    @SerializedName("thumbnail"        ) var thumbnail       : String?                  = null,
    @SerializedName("pictures"         ) var pictures        : ArrayList<PicturesDTO>   = arrayListOf(),
    @SerializedName("attributes"       ) var attributes      : ArrayList<AttributesDTO> = arrayListOf(),
    @SerializedName("listing_source"   ) var listingSource   : String?                  = null,
    @SerializedName("status"           ) var status          : String?                  = null,
    @SerializedName("warranty"         ) var warranty        : String?                  = null,
)

fun DetailsResponseDTO.toDetailsResponseUI() = DetailsResponseUI(
    id = id,
    title = title,
    price = Utilities.formatFloatToCurrency(price),
    basePrice = Utilities.formatFloatToCurrency(basePrice),
    condition = if (condition == "used") "Producto usado" else "Producto nuevo",
    thumbnail = thumbnail,
    pictures = pictures.map { it.url } as ArrayList<String>,
    attributes = attributes.map { mapOf("name" to it.name, "valueName" to it.valueName) } as ArrayList<Map<String, String>>,
    warranty = "$warranty"
)
