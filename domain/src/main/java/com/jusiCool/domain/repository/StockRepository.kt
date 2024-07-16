package com.jusiCool.domain.repository

import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getStockDetail(stockId: String): Flow<GetStockDetailResponseModel>
    suspend fun getStockList(): Flow<GetStockListResponseModel>
    suspend fun buyStock(stockId: String, body: StockRequestModel): Flow<Unit>
    suspend fun sellStockReserve(stockId: String, body: BuyStockRequestModel): Flow<Unit>
    suspend fun buyStockReserve(stockId: String, body: BuyStockRequestModel): Flow<Unit>
    suspend fun deleteStock(stockId: String, body: StockRequestModel): Flow<Unit>
}