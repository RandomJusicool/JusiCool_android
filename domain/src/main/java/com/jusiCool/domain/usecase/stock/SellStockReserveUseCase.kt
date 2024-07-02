package com.jusiCool.domain.usecase.stock

import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.jusiCool.domain.repository.StockRepository
import javax.inject.Inject

class SellStockReserveUseCase @Inject constructor(
    private val repository: StockRepository
) {
    suspend operator fun invoke(
        stockId: Long,
        body: BuyStockRequestModel
    ) = runCatching {
        repository.sellStockReserve(
            stockId = stockId,
            body = body
        )
    }
}