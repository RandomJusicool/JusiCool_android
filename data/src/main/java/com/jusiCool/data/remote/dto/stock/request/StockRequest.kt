package com.jusiCool.data.remote.dto.stock.request

import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockRequest(
    @Json(name = "num") val num: Long
)

fun StockRequestModel.toDto() = StockRequest(num = num)