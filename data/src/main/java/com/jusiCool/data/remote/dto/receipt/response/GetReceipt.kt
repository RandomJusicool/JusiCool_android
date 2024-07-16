package com.jusiCool.data.remote.dto.receipt.response

import com.jusiCool.domain.enumtype.ReceiptEnumType
import com.jusiCool.domain.model.receipt.response.GetReceiptModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetReceipt(
    @Json(name = "status") val status: ReceiptEnumType,
    @Json(name = "stockName") val stockName: String,
    @Json(name = "price") val price: Long,
)

fun GetReceipt.toModel() = GetReceiptModel(
    status = status,
    stockName = stockName,
    price = price,
)