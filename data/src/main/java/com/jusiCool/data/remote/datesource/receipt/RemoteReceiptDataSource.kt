package com.jusiCool.data.remote.datesource.receipt

import com.jusiCool.data.remote.dto.receipt.response.GetReceipt
import kotlinx.coroutines.flow.Flow

interface RemoteReceiptDataSource {
    suspend fun getReceipt(status: String) : Flow<List<GetReceipt>>
}