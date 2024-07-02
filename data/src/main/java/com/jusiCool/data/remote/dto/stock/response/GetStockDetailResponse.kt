package com.jusiCool.data.remote.dto.stock.response

import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetStockDetailResponse(
    @Json(name = "name") val name: String,
    @Json(name = "code") val code: Int,
    @Json(name = "marketPrice") val marketPrice: Long,
    @Json(name = "headPrice") val headPrice: Long,
    @Json(name = "highPrice") val highPrice: Long,
    @Json(name = "lowPrice") val lowPrice: Long,
    @Json(name = "presentPrice") val presentPrice: Long,
    @Json(name = "contractPrice") val contractPrice: Long,
    @Json(name = "fluctuationComparedPreviousDay") val fluctuationComparedPreviousDay: Long,
    @Json(name = "marketCapitalization") val marketCapitalization: Long,
    @Json(name = "transactionVolume") val transactionVolume: Long,
    @Json(name = "transactionPrice") val transactionPrice: Long
)

fun GetStockDetailResponse.toModel() = GetStockDetailResponseModel(
    name = name,
    code = code,
    marketPrice = marketPrice,
    headPrice = headPrice,
    highPrice = highPrice,
    lowPrice = lowPrice,
    presentPrice = presentPrice,
    contractPrice = contractPrice,
    fluctuationComparedPreviousDay = fluctuationComparedPreviousDay,
    marketCapitalization = marketCapitalization,
    transactionVolume = transactionVolume,
    transactionPrice = transactionPrice
)