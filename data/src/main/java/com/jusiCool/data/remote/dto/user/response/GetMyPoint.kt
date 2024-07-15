package com.jusiCool.data.remote.dto.user.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMyPoint(
    @Json(name = "points") val points: Long,
    @Json(name = "upDownPercent") val upDownPercent: Double,
    @Json(name = "upDownPoints") val upDownPoints: Long,
)
