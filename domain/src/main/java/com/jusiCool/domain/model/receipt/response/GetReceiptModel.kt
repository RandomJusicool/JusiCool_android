package com.jusiCool.domain.model.receipt.response

import com.jusiCool.domain.enumtype.ReceiptEnumType

data class GetReceiptModel(
    val status: ReceiptEnumType,
    val stockName: String,
    val price: Long,
)