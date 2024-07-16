package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.stock.RemoteStockDataSource
import com.jusiCool.data.remote.dto.board.response.toModel
import com.jusiCool.data.remote.dto.stock.request.toDto
import com.jusiCool.data.remote.dto.stock.response.toModel
import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import com.jusiCool.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val dataSource: RemoteStockDataSource
) : StockRepository {
    override suspend fun getStockDetail(stockId: String): Flow<GetStockDetailResponseModel> {
        return dataSource.getStockDetail(stockId = stockId).map { it.toModel() }
    }

    override suspend fun getStockList(): Flow<List<GetStockListResponseModel>> {
        return dataSource.getStockList().map { list -> list.map { it.toModel() } }
    }

    override suspend fun buyStock(stockId: String, body: StockRequestModel): Flow<Unit> {
        return dataSource.buyStock(
            stockId = stockId,
            body = body.toDto()
        )
    }

    override suspend fun sellStockReserve(stockId: String, body: BuyStockRequestModel): Flow<Unit> {
        return dataSource.sellStockReserve(
            stockId = stockId,
            body = body.toDto()
        )
    }

    override suspend fun buyStockReserve(stockId: String, body: BuyStockRequestModel): Flow<Unit> {
        return dataSource.buyStockReserve(
            stockId = stockId,
            body = body.toDto()
        )
    }

    override suspend fun deleteStock(stockId: String, body: StockRequestModel): Flow<Unit> {
        return dataSource.deleteStock(
            stockId = stockId,
            body = body.toDto()
        )
    }
}