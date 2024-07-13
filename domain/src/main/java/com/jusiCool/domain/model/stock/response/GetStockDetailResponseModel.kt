package com.jusiCool.domain.model.stock.response

data class GetStockDetailResponseModel(
    val name: String,
    val code: Int,
    val upDownPrice: Long,
    val upDownPercent: Double,
    val presentPrice: Long,
    val transactionVolume: Long? = 0, // nullable로 수정하고 기본값 설정
    val transactionPrice: Long
)
