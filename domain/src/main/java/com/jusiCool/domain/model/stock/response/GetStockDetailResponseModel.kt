package com.jusiCool.domain.model.stock.response

data class GetStockDetailResponseModel(
    val name: String,
    val code: Int,
    val upDownPrice: Long, // 음수와 양수 존재
    val upDownPercent: Double,
    val presentPrice: Long,
    val transactionVolume: Long,
    val transactionPrice: Long,
)