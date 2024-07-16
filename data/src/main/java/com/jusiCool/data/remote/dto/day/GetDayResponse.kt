package com.jusiCool.data.remote.dto.day

import com.jusiCool.domain.model.day.GetDayModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetDayResponse(
    @Json(name = "marketPrice") val marketPrice: Long, // 시가
    @Json(name = "highPrice") val highPrice: Long, // 고가
    @Json(name = "lowPrice") val lowPrice: Long, // 저가
    @Json(name = "presentPrice") val presentPrice: Long, // 현재가
    @Json(name = "volume") val volume: Long, // 거래량
    @Json(name = "upDownPercent") val upDownPercent: Double, // 등락률
    @Json(name = "storeAt") val storeAt: String // 저장된 시간
)

fun GetDayResponse.toModel(): GetDayModel = GetDayModel(
    marketPrice = marketPrice,
    highPrice = highPrice,
    lowPrice = lowPrice,
    presentPrice = presentPrice,
    volume = volume,
    upDownPercent = upDownPercent,
    storeAt = storeAt,
)