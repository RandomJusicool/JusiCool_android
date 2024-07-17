package com.jusiCool.domain.usecase.stock

import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.jusiCool.domain.repository.StockRepository
import javax.inject.Inject

class BuyStockReserveUseCase @Inject constructor(
    private val repository: StockRepository
) {
    suspend operator fun invoke(
        stockId: String,
        body: BuyStockRequestModel
    ) = runCatching {
        repository.buyStockReserve(
            stockId = stockId,
            body = body
        )
    }
}