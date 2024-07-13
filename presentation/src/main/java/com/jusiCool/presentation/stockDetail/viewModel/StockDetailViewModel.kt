package com.jusiCool.presentation.stockDetail.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.jusiCool.domain.usecase.day.GetDayUseCase
import com.jusiCool.domain.usecase.stock.GetStockDetailUseCase
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
    private val _stockDetail: MutableStateFlow<GetStockDetailResponseModel> = MutableStateFlow(
        GetStockDetailResponseModel(
            name = "",
            code = 0,
            upDownPrice = 0,
            upDownPercent = 0.0,
            presentPrice = 0,
            transactionVolume = 0,
            transactionPrice = 0,
        )
    )
    val stockDetail = _stockDetail.asStateFlow()

    fun getStockDetail(stockId: Long) = viewModelScope.launch {
        getStockDetailUseCase(stockId)
            .onSuccess {
                it.catch { remoteError ->
                    Log.e("Error", "Error collecting data", remoteError)
                }.collect { data ->
                    Log.d("data", data.name)
                    _stockDetail.value = data
                }
            }
            .onFailure { error ->
                Log.e("Error", "Error fetching data", error)
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