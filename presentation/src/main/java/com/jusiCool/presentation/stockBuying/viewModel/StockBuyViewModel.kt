package com.jusiCool.presentation.stockBuying.viewModel

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.jusiCool.domain.usecase.stock.BuyStockUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockBuyViewModel @Inject constructor(
    private val buyStockUseCase: BuyStockUseCase
) : ViewModel() {
    private val _postBuyStockResponse = MutableStateFlow<Event<StockRequestModel>>(Event.Loading)
    val postBuyStockResponse = _postBuyStockResponse.asStateFlow()

    var stockText = mutableLongStateOf(0)
        private set

    internal fun postBuyStock(
        stockId: Long,
        num: Long
    ) = viewModelScope.launch {
        buyStockUseCase(
            stockId = stockId,
            body = StockRequestModel(
                num = num
            )
        ).onSuccess {
            it.catch { remoteError ->
                _postBuyStockResponse.value = remoteError.errorHandling()
            }.collect {
                _postBuyStockResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _postBuyStockResponse.value = error.errorHandling()
        }
    }
}