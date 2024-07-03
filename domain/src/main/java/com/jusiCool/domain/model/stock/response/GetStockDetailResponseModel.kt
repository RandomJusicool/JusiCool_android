package com.jusiCool.domain.model.stock.response

data class GetStockDetailResponseModel(
    val name: String,
    val code: Int,
    val marketPrice: Long,
    val headPrice: Long,
    val highPrice: Long,
    val lowPrice: Long,
    val presentPrice: Long,
    val contractPrice: Long,
    val fluctuationComparedPreviousDay: Long,
    val marketCapitalization: Long,
    val transactionVolume: Long,
    val transactionPrice: Long
)
