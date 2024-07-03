package com.jusiCool.data.remote.dto.stock.request

import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BuyStockRequest(
    @Json(name = "num") val num: Long,
    @Json(name = "goal_price") val goal_price: Long,
)

fun BuyStockRequestModel.toDto() = BuyStockRequest(
    num = num,
    goal_price = goal_price
)