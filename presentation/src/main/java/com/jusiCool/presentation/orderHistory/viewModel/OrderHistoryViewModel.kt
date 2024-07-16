package com.jusiCool.presentation.orderHistory.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.enumtype.ReceiptEnumType
import com.jusiCool.domain.model.receipt.response.GetReceiptModel
import com.jusiCool.domain.usecase.receipt.GetReceiptUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val getReceiptUseCase: GetReceiptUseCase
) : ViewModel() {
    private val _swipeRefreshLoading = MutableStateFlow(false)
    val swipeRefreshLoading = _swipeRefreshLoading.asStateFlow()

    private val _getReceiptResponse = MutableStateFlow<Event<List<GetReceiptModel>>>(Event.Loading)
    val getReceiptResponse = _getReceiptResponse.asStateFlow()

    private val _getBuyReceipt = MutableStateFlow<List<GetReceiptModel>>(emptyList())
    val getBuyReceipt = _getBuyReceipt.asStateFlow()

    private val _getSellReceipt = MutableStateFlow<List<GetReceiptModel>>(emptyList())
    val getSellReceipt = _getSellReceipt.asStateFlow()

    init {
        loadStuff()
    }

    private fun loadStuff() {
        viewModelScope.launch {
            _swipeRefreshLoading.value = true
            delay(700L)
            fetchReceipt()
            _swipeRefreshLoading.value = false
        }
    }

    internal fun fetchReceipt() = viewModelScope.launch {
        getReceiptUseCase().onSuccess { it ->
            it.catch { remoteError ->
                _getReceiptResponse.value = remoteError.errorHandling()
            }.collect { response ->
                val buyReceipts = response.filter { it.status == ReceiptEnumType.BUY }
                val sellReceipts = response.filter { it.status == ReceiptEnumType.SELL }

                _getBuyReceipt.value = buyReceipts
                _getSellReceipt.value = sellReceipts

                _getReceiptResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getReceiptResponse.value = error.errorHandling()
        }
    }

    internal fun refreshReceipts() = loadStuff()
}