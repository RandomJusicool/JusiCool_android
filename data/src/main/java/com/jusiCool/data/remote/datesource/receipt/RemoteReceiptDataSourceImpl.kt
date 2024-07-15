package com.jusiCool.data.remote.datesource.receipt

import com.jusiCool.data.remote.api.ReceiptAPI
import com.jusiCool.data.remote.dto.receipt.response.GetReceipt
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteReceiptDataSourceImpl @Inject constructor(
    private val serviceReceipt: ReceiptAPI
) : RemoteReceiptDataSource {
    override suspend fun getReceipt(status: String): Flow<List<GetReceipt>> =
        performApiRequest { serviceReceipt.getReceipt(status = status) }
}