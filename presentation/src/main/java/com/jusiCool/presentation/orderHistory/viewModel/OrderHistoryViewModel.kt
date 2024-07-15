package com.jusiCool.presentation.orderHistory.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.receipt.response.GetReceiptModel
import com.jusiCool.domain.usecase.receipt.GetReceiptUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val getReceiptUseCase: GetReceiptUseCase
) : ViewModel() {
    private val _getReceiptResponse = MutableStateFlow<Event<List<GetReceiptModel>>>(Event.Loading)
    val getReceiptResponse = _getReceiptResponse.asStateFlow()

    internal fun getReceipt() = viewModelScope.launch {
        getReceiptUseCase().onSuccess {
            it.catch { remoteError ->
                _getReceiptResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getReceiptResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getReceiptResponse.value = error.errorHandling()
        }
    }
}