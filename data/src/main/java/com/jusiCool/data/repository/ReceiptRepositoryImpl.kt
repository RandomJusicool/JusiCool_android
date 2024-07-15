package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.receipt.RemoteReceiptDataSource
import com.jusiCool.data.remote.dto.receipt.response.toModel
import com.jusiCool.domain.model.receipt.response.GetReceiptModel
import com.jusiCool.domain.repository.ReceiptRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReceiptRepositoryImpl @Inject constructor(
    private val dataSource: RemoteReceiptDataSource
) : ReceiptRepository {
    override suspend fun getReceipt(): Flow<List<GetReceiptModel>> {
        return dataSource.getReceipt().map { list -> list.map { it.toModel() } }
    }
}