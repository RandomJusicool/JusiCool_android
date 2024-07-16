package com.jusiCool.presentation.main.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.board.response.GetCommunityBoardDetailResponseModel
import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import com.jusiCool.domain.model.user.response.GetMyPointModel
import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.jusiCool.domain.usecase.stock.GetStockListUseCase
import com.jusiCool.domain.usecase.user.GetMyPointUseCase
import com.jusiCool.domain.usecase.user.GetMyStockUseCase
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
class MainViewModel @Inject constructor(
    private val getMyPointUseCase: GetMyPointUseCase,
    private val getMyStockUseCase: GetMyStockUseCase,
    private val getStockListUseCase: GetStockListUseCase
) : ViewModel() {
    private val _swipeRefreshLoading = MutableStateFlow(false)
    val swipeRefreshLoading = _swipeRefreshLoading.asStateFlow()

    private val _getMyPointResponse = MutableStateFlow<Event<GetMyPointModel>>(Event.Loading)
    val getMyPointResponse = _getMyPointResponse.asStateFlow()

    private val _getMyStockResponse = MutableStateFlow<Event<List<GetMyStockModel>>>(Event.Loading)
    val getMyStockResponse = _getMyStockResponse.asStateFlow()

    private val _getStockListResponse = MutableStateFlow<Event<List<GetStockListResponseModel>>>(Event.Loading)
    val getStockListResponse = _getStockListResponse.asStateFlow()

    init {
        loadStuff()
    }

    fun loadStuff() {
        viewModelScope.launch {
            _swipeRefreshLoading.value = true
            delay(1000L)
            _swipeRefreshLoading.value = false
        }
    }

    var myStock = mutableStateListOf<GetMyStockModel>()
        private set

    var stockList = mutableStateListOf<GetStockListResponseModel>()
        private set

    var myPoint = mutableStateOf(
        GetMyPointModel(
            points = 0,
            upDownPercent = 0.0,
            upDownPoints = 0,
        )
    )
        private set

    internal fun getMyPoint() = viewModelScope.launch {
        getMyPointUseCase().onSuccess {
            it.catch { remoteError ->
                _getMyPointResponse.value = remoteError.errorHandling()
                Log.d("MainViewModel", "Error while collecting GetMyPointUseCase response: ${remoteError.message}")
            }.collect { response ->
                _getMyPointResponse.value = Event.Success(data = response)
                Log.d("MainViewModel", "Successfully collected GetMyPointUseCase response: $response")
            }
        }.onFailure { error ->
            _getMyPointResponse.value = error.errorHandling()
            Log.d("MainViewModel", "Failed to execute GetMyPointUseCase: ${error.message}")
        }
    }

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

    internal fun getStockList() = viewModelScope.launch {
        getStockListUseCase().onSuccess {
            it.catch { remoteError ->
                _getStockListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStockListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStockListResponse.value = error.errorHandling()
        }
    }
}