package com.jusiCool.presentation.stockReservationSelling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.jusiCool.domain.usecase.stock.SellStockReserveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockReservationSellingRouteViewModel @Inject constructor(
    private val sellStockReserveUseCase: SellStockReserveUseCase,
) : ViewModel() {
    fun sellStockReserve(
        stockId: String,
        body: BuyStockRequestModel,
        onSuccess: () -> Unit,
    ) = viewModelScope.launch {
        sellStockReserveUseCase(
            stockId = stockId,
            body = body
        )
            .onSuccess {
                it.catch {

                }.collect {
                    onSuccess()
                }
            }.onFailure {

            }
    }
}