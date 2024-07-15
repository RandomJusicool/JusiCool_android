package com.jusiCool.domain.repository

import com.jusiCool.domain.model.receipt.response.GetReceiptModel
import kotlinx.coroutines.flow.Flow

interface ReceiptRepository {
    suspend fun getReceipt(status: String) : Flow<List<GetReceiptModel>>
}