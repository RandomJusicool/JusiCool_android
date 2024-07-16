package com.jusiCool.data.remote.dto.stock.response

import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetStockListResponse(
    @Json(name = "code") val code: String,
    @Json(name = "name") val name: String,
    @Json(name = "presentPrice") val present_price: Long,
    @Json(name = "upDownPrice") val upDownPrice: Long,
    @Json(name = "upDownPercent") val upDownPercent: Double
)

fun GetStockListResponse.toModel() = GetStockListResponseModel(
    code = code,
    name = name,
    present_price = present_price,
    upDownPrice = upDownPrice,
    upDownPercent = upDownPercent
)