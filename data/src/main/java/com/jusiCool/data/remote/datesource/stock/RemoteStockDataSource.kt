package com.jusiCool.data.remote.datesource.stock

import com.jusiCool.data.remote.dto.stock.request.BuyStockRequest
import com.jusiCool.data.remote.dto.stock.request.StockRequest
import com.jusiCool.data.remote.dto.stock.response.GetStockDetailResponse
import com.jusiCool.data.remote.dto.stock.response.GetStockListResponse
import kotlinx.coroutines.flow.Flow

interface RemoteStockDataSource {
    suspend fun getStockDetail(stockId: String): Flow<GetStockDetailResponse>
    suspend fun getStockList(): Flow<List<GetStockListResponse>>
    suspend fun buyStock(stockId: String, body: StockRequest): Flow<Unit>
    suspend fun sellStockReserve(stockId: String, body: BuyStockRequest): Flow<Unit>
    suspend fun buyStockReserve(stockId: String, body: BuyStockRequest): Flow<Unit>
    suspend fun deleteStock(stockId: String, body: StockRequest): Flow<Unit>
}