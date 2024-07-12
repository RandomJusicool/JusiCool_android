package com.jusiCool.data.remote.dto.stock.response

import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetStockDetailResponse(
    @Json(name = "name") val name: String,
    @Json(name = "code") val code: Int,
    @Json(name = "upDownPrice") val upDownPrice: Long,
    @Json(name = "upDownPercent") val upDownPercent: Double,
    @Json(name = "presentPrice") val presentPrice: Long,
    @Json(name = "transactionVolume") val transactionVolume: Long?=0,
    @Json(name = "transactionPrice") val transactionPrice: Long
)

fun GetStockDetailResponse.toModel() = GetStockDetailResponseModel(
    name = name,
    code = code,
    upDownPrice = upDownPrice,
    upDownPercent = upDownPercent,
    presentPrice = presentPrice,
    transactionVolume = transactionVolume,
    transactionPrice = transactionPrice
)