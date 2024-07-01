package com.jusiCool.domain.model.reservation.response

import com.jusiCool.domain.enumtype.ReserveStockEnumType

data class GetReserveStockResponseModel(
    val stock_name: String,
    val stock_num: Int,
    val status: ReserveStockEnumType
)
