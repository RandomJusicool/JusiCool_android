package com.jusiCool.data.remote.dto.reservation.response

import com.jusiCool.domain.enumtype.ReserveStockEnumType
import com.jusiCool.domain.model.reservation.response.GetReserveStockResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetReserveStockResponse(
    @Json(name = "stock_name") val stock_name: String,
    @Json(name = "stock_num") val stock_num: Int,
    @Json(name = "status") val status: ReserveStockEnumType
)

fun GetReserveStockResponse.toModel() = GetReserveStockResponseModel(
    stock_name = stock_name,
    stock_num = stock_num,
    status = status
)