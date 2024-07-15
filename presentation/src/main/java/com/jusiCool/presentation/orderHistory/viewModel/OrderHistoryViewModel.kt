package com.jusiCool.presentation.orderHistory.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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

    var getBuyReceipt = mutableStateListOf<GetReceiptModel>()
        private set

    var getSellReceipt = mutableStateListOf<GetReceiptModel>()
        private set

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

    private fun fetchReceipt() = viewModelScope.launch {
        getReceiptUseCase().onSuccess { it ->
            it.catch { remoteError ->
                _getReceiptResponse.value = remoteError.errorHandling()
            }.collect { response ->
                val buyReceipts = response.filter { it.status == ReceiptEnumType.BUY }
                val sellReceipts = response.filter { it.status == ReceiptEnumType.SELL }

                this@OrderHistoryViewModel.getBuyReceipt.clear()
                this@OrderHistoryViewModel.getBuyReceipt.addAll(buyReceipts)

                this@OrderHistoryViewModel.getSellReceipt.clear()
                this@OrderHistoryViewModel.getSellReceipt.addAll(sellReceipts)

                _getReceiptResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getReceiptResponse.value = error.errorHandling()
        }
    }

    internal fun refreshReceipts() = loadStuff()
}