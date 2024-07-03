package com.jusiCool.domain.model.stock.response

data class GetStockListResponseModel(
    val id: Long,
    val name: String,
    val presentPrice: Int,
    val fluctuationComparedPreviousDay: Int,
)