package com.jusiCool.presentation.stockReservationBuy.viewmodel

import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.stock.request.BuyStockRequestModel
import com.jusiCool.domain.usecase.stock.BuyStockReserveUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockReservationBuyViewModel @Inject constructor(
    private val buyStockReserveUseCase: BuyStockReserveUseCase
) : ViewModel() {

    private val _buyStockReserveResponse = MutableStateFlow<Event<BuyStockReserveUseCase>>(Event.Loading)
    val buyStockReserveResponse = _buyStockReserveResponse.asStateFlow()

    var postStock = mutableLongStateOf(0)
        private set

    var postReserveStock = mutableLongStateOf(0)
        private set

    fun postBuyStock(
        stockId: String,
        num: Long,
        goal_price: Long,
    ) = viewModelScope.launch {
        buyStockReserveUseCase(
            stockId = stockId,
            body = BuyStockRequestModel(
                num = num,
                goal_price = goal_price
            )
        ).onSuccess {
                it.catch { remoteError ->
                    _buyStockReserveResponse.value = remoteError.errorHandling()
                }.collect {
                    _buyStockReserveResponse.value = Event.Success()
                }
            }.onFailure { error ->
                _buyStockReserveResponse.value = error.errorHandling()
        }
    }
}