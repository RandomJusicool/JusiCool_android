package com.jusiCool.presentation.stockReservationBuying.screen.stockSell.viewModel

import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.jusiCool.domain.usecase.stock.DeleteStockUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockSellViewModel @Inject constructor(
    private val deleteStockUseCase: DeleteStockUseCase
) : ViewModel() {
    private val _deleteStockResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deleteStockResponse = _deleteStockResponse.asStateFlow()

    var stockText = mutableLongStateOf(0)
        private set

    internal fun deleteStock(
        stockId: String,
        num: Long
    ) = viewModelScope.launch {
        deleteStockUseCase(
            stockId = stockId,
            body = StockRequestModel(num = num)
        ).onSuccess {
            it.catch { remoteError ->
                _deleteStockResponse.value = remoteError.errorHandling()
            }.collect {
                _deleteStockResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _deleteStockResponse.value = error.errorHandling()
        }
    }
}