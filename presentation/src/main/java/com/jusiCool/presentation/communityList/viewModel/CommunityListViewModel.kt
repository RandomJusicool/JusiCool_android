package com.jusiCool.presentation.communityList.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel
import com.jusiCool.domain.repository.CommunityRepository
import com.jusiCool.domain.usecase.community.GetCommunityListUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityListViewModel @Inject constructor(
    private val getCommunityListUseCase: GetCommunityListUseCase,
) : ViewModel() {
    private val _swipeRefreshLoading = MutableStateFlow(false)
    val swipeRefreshLoading = _swipeRefreshLoading.asStateFlow()

    private val _getCommunityListResponse = MutableStateFlow<Event<List<GetCommunityListResponseModel>>>(Event.Loading)
    val getCommunityListResponse = _getCommunityListResponse.asStateFlow()

    private lateinit var _communityData : MutableState<GetCommunityListResponseModel>
    val communityData: MutableState<GetCommunityListResponseModel>
        get() = _communityData

    init {
        loadStuff()
    }

    fun loadStuff() {
        viewModelScope.launch {
            _swipeRefreshLoading.value = true
            delay(700L)
            _swipeRefreshLoading.value = false
        }
    }

    var communityList = mutableStateListOf<GetCommunityListResponseModel>()
        private set

    internal fun getCommunityList() = viewModelScope.launch {
        getCommunityListUseCase().onSuccess {
            it.catch { remoteError ->
                _getCommunityListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getCommunityListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getCommunityListResponse.value = error.errorHandling()
        }
    }
}