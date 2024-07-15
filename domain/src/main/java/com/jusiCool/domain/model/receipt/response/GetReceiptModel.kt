package com.jusiCool.domain.model.receipt.response

import com.jusiCool.domain.enumtype.ReceiptEnumType

data class GetReceiptModel(
    val stockName: String,
    val status: ReceiptEnumType,
    val price: Long,
)