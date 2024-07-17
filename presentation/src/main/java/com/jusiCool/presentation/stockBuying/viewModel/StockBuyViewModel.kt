package com.jusiCool.presentation.stockBuying.viewModel

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.stock.request.StockRequestModel
import com.jusiCool.domain.model.user.response.GetMyPointModel
import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.jusiCool.domain.usecase.stock.BuyStockUseCase
import com.jusiCool.domain.usecase.user.GetMyPointUseCase
import com.jusiCool.domain.usecase.user.GetMyStockUseCase
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
    private val getMyStockUseCase: GetMyStockUseCase,
    private val getMyPointUseCase: GetMyPointUseCase,
    private val buyStockUseCase: BuyStockUseCase
) : ViewModel() {
    private val _postBuyStockResponse = MutableStateFlow<Event<StockRequestModel>>(Event.Loading)
    val postBuyStockResponse = _postBuyStockResponse.asStateFlow()

    private val _getMyStockResponse = MutableStateFlow<Event<List<GetMyStockModel>>>(Event.Loading)
    val getMyStockResponse = _getMyStockResponse.asStateFlow()

    private val _getMyPointResponse = MutableStateFlow<Event<GetMyPointModel>>(Event.Loading)
    val getMyPointResponse = _getMyPointResponse.asStateFlow()

    var myStock = mutableStateListOf<GetMyStockModel>()
    private set

    var myPoint = mutableStateOf(
        GetMyPointModel(
            points = 0,
            upDownPercent = 0.0,
            upDownPoints = 0,
        )
    )
        private set

    var stockText = mutableLongStateOf(0L)
        private set


    internal fun getMyStock() = viewModelScope.launch {
        getMyStockUseCase().onSuccess {
            it.catch { remoteError ->
                _getMyStockResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getMyStockResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getMyStockResponse.value = error.errorHandling()
        }
    }

    internal fun getMyPoint() = viewModelScope.launch {
        getMyPointUseCase().onSuccess {
            it.catch { remoteError ->
                _getMyPointResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getMyPointResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getMyPointResponse.value = error.errorHandling()
        }
    }

    internal fun postBuyStock(
        stockId: String,
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