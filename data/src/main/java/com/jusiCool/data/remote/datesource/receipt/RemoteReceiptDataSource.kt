package com.jusiCool.data.remote.datesource.receipt

import com.jusiCool.data.remote.dto.receipt.response.GetReceipt
import kotlinx.coroutines.flow.Flow

interface RemoteReceiptDataSource {
    suspend fun getReceipt() : Flow<List<GetReceipt>>
}