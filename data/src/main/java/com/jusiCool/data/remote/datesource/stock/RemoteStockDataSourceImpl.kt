package com.jusiCool.data.remote.datesource.stock

import com.jusiCool.data.remote.api.StockAPI
import com.jusiCool.data.remote.dto.stock.request.BuyStockRequest
import com.jusiCool.data.remote.dto.stock.request.StockRequest
import com.jusiCool.data.remote.dto.stock.response.GetStockDetailResponse
import com.jusiCool.data.remote.dto.stock.response.GetStockListResponse
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteStockDataSourceImpl @Inject constructor(
    private val serviceStock: StockAPI
) : RemoteStockDataSource {
    override suspend fun getStockDetail(stockId: String): Flow<GetStockDetailResponse> =
        performApiRequest { serviceStock.getStockDetail(stockId = stockId) }

    override suspend fun getStockList(): Flow<GetStockListResponse> =
        performApiRequest { serviceStock.getStockList() }

    override suspend fun buyStock(stockId: String, body: StockRequest): Flow<Unit> =
        performApiRequest { serviceStock.buyStock(
            stockId = stockId,
            body = body
        ) }

    override suspend fun sellStockReserve(stockId: String, body: BuyStockRequest): Flow<Unit> =
        performApiRequest { serviceStock.sellStockReserve(
            stockId = stockId,
            body = body
        ) }

    override suspend fun buyStockReserve(stockId: String, body: BuyStockRequest): Flow<Unit> =
        performApiRequest { serviceStock.buyStockReserve(
            stockId = stockId,
            body = body
        ) }

    override suspend fun deleteStock(stockId: String, body: StockRequest): Flow<Unit> =
        performApiRequest { serviceStock.deleteStock(
            stockId = stockId,
            body = body
        ) }
}