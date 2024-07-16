package com.jusiCool.presentation.community.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel
import com.jusiCool.domain.repository.CommunityRepository
import com.jusiCool.domain.usecase.board.GetCommunityBoardListUseCase
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
class CommunityViewModel @Inject constructor(
    private val getCommunityBoardListUseCase: GetCommunityBoardListUseCase,
): ViewModel() {
    private val _swipeRefreshLoading = MutableStateFlow(false)
    val swipeRefreshLoading = _swipeRefreshLoading.asStateFlow()

    private val _getCommunityListBoardResponse = MutableStateFlow<Event<List<GetCommunityBoardListResponseModel>>>(Event.Loading)
    val getCommunityListBoardResponse = _getCommunityListBoardResponse.asStateFlow()

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

    var communityListBoard = mutableStateListOf<GetCommunityBoardListResponseModel>()
        private set

    internal fun getListBoard(communityId: String) = viewModelScope.launch {
        getCommunityBoardListUseCase(communityId = communityId).onSuccess {
            it.catch { remoteError ->
                _getCommunityListBoardResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getCommunityListBoardResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getCommunityListBoardResponse.value = error.errorHandling()
        }
    }
}