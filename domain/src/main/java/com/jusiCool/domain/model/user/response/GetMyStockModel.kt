package com.jusiCool.domain.model.user.response

data class GetMyStockModel(
    val code: String,
    val stock_name: String,
    val stock_num: Long,
    val points: Long,
    val upDownPercent: Double,
    val upDownPoints: Long,
)
