package com.jusiCool.presentation.stockDetail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.jusiCool.domain.usecase.day.GetDayUseCase
import com.jusiCool.domain.usecase.stock.GetStockDetailUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val getStockDetailUseCase: GetStockDetailUseCase,
    private val getDayUseCase: GetDayUseCase
) : ViewModel() {
    private val _stockDetail: MutableStateFlow<Event<GetStockDetailResponseModel>> =
        MutableStateFlow(Event.Loading)
    val stockDetailData = _stockDetail.asStateFlow()

    fun getStockDetail(stockId: Long) = viewModelScope.launch {
        getStockDetailUseCase(stockId)
            .onSuccess {
                it.catch { remoteError ->
                    _stockDetail.value = remoteError.errorHandling()
                }.collect { data ->
                    _stockDetail.value = Event.Success(data)
                }
            }
            .onFailure { error ->
                _stockDetail.value = error.errorHandling()
            }
    }

    fun getDay(stockCode: String) = viewModelScope.launch {
        getDayUseCase(stockCode)
            .onSuccess {
                it.catch {

                }.collect {

                }
            }.onFailure { }
    }
}