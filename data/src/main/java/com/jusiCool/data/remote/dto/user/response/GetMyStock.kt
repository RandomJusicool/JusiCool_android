package com.jusiCool.data.remote.dto.user.response

import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMyStock(
    @Json(name = "stock_name") val stock_name: String,
    @Json(name = "stock_num") val stock_num: Long,
    @Json(name = "points") val points: Long,
    @Json(name = "upDownPercent") val upDownPercent: Double,
    @Json(name = "upDownPoints") val upDownPoints: Long,
)

fun GetMyStock.toModel() = GetMyStockModel(
    stock_name = stock_name,
    stock_num = stock_num,
    points = points,
    upDownPercent = upDownPercent,
    upDownPoints = upDownPoints,
)