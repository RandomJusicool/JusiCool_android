package com.jusiCool.domain.usecase.stock

import com.jusiCool.domain.repository.StockRepository
import javax.inject.Inject

class GetStockDetailUseCase @Inject constructor(
    private val repository: StockRepository
) {
    suspend operator fun invoke(stockId: String) = runCatching {
        repository.getStockDetail(stockId = stockId)
    }
}