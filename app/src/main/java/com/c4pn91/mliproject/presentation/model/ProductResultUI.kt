package com.c4pn91.mliproject.presentation.model

data class ProductResultUI(
    var id                 : String?  = null,
    var title              : String?  = null,
    var condition          : String?  = null,
    var thumbnail          : String?  = null,
    var price              : String?  = null,
    var originalPrice      : String?  = null,
    var availableQuantity  : String?  = null,
    var acceptsMercadopago : String?  = null,
)