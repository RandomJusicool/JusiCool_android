package com.jusiCool.data.remote.datesource.stock

import com.jusiCool.data.remote.dto.stock.request.BuyStockRequest
import com.jusiCool.data.remote.dto.stock.request.StockRequest
import com.jusiCool.data.remote.dto.stock.response.GetStockDetailResponse
import com.jusiCool.data.remote.dto.stock.response.GetStockListResponse
import kotlinx.coroutines.flow.Flow

interface RemoteStockDataSource {
    suspend fun getStockDetail(stockId: Long) : Flow<GetStockDetailResponse>
    suspend fun getStockList() : Flow<GetStockListResponse>
    suspend fun buyStock(stockId: Long, body: StockRequest) : Flow<Unit>
    suspend fun sellStockReserve(stockId: Long, body: BuyStockRequest) : Flow<Unit>
    suspend fun buyStockReserve(stockId: Long, body: BuyStockRequest) : Flow<Unit>
    suspend fun deleteStock(stockId: Long) : Flow<Unit>
}