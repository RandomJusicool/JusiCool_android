package com.jusiCool.domain.usecase.stock

import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.jusiCool.domain.repository.StockRepository
import javax.inject.Inject

class BuyStockUseCase @Inject constructor(
    private val repository: StockRepository
) {
    suspend operator fun invoke(
        stockId: String,
        body: StockRequestModel
    ) = runCatching {
        repository.buyStock(
            stockId = stockId,
            body = body
        )
    }
}