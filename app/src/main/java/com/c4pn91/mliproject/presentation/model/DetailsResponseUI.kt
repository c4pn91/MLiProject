package com.c4pn91.mliproject.presentation.model

data class DetailsResponseUI(
    var id              : String?                        = null,
    var title           : String?                        = null,
    var price           : String?                        = null,
    var basePrice       : String?                        = null,
    var condition       : String?                        = null,
    var thumbnail       : String?                        = null,
    var pictures        : ArrayList<String>              = arrayListOf(),
    var attributes      : ArrayList<Map<String, String>> = arrayListOf(),
    var warranty        : String?                        = null,
)