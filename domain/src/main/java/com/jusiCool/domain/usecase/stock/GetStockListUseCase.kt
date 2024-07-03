package com.jusiCool.domain.usecase.stock

import com.jusiCool.domain.repository.StockRepository
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(
    private val repository: StockRepository
) {
    suspend operator fun invoke() = runCatching {
        repository.getStockList()
    }
}