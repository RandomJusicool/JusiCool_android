package com.jusiCool.domain.model.stock.response

data class GetStockListResponseModel(
    val code: String,
    val name: String,
    val present_price: Long,
    val upDownPrice: Long,
    val upDownPercent: Double
)