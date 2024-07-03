package com.jusiCool.domain.repository

import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getStockDetail(stockId: Long) : Flow<GetStockDetailResponseModel>
    suspend fun getStockList() : Flow<GetStockListResponseModel>
    suspend fun buyStock(stockId: Long, body: StockRequestModel) : Flow<Unit>
    suspend fun sellStockReserve(stockId: Long, body: BuyStockRequestModel) : Flow<Unit>
    suspend fun buyStockReserve(stockId: Long, body: BuyStockRequestModel) : Flow<Unit>
    suspend fun deleteStock(stockId: Long) : Flow<Unit>
}