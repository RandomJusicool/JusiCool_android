package com.jusiCool.data.remote.dto.stock.response

import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetStockListResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "presentprice") val presentPrice: Int,
    @Json(name = "fluctuationcomparedpreviousday") val fluctuationComparedPreviousDay: Int
)

fun GetStockListResponse.toModel() = GetStockListResponseModel(
    id = id,
    name = name,
    presentPrice = presentPrice,
    fluctuationComparedPreviousDay = fluctuationComparedPreviousDay
)