package com.jusiCool.presentation.communityDetail.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.model.board.response.GetCommunityBoardDetailResponseModel
import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel
import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import com.jusiCool.domain.usecase.board.DeleteCommunityBoardUseCase
import com.jusiCool.domain.usecase.board.GetCommunityDetailUseCase
import com.jusiCool.domain.usecase.board.PatchCommunityBoardUseCase
import com.jusiCool.domain.usecase.comment.GetCommunityCommentUseCase
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
class CommunityDetailViewModel @Inject constructor(
    private val getCommunityDetailUseCase: GetCommunityDetailUseCase,
    private val getCommunityCommentUseCase: GetCommunityCommentUseCase,
    private val deleteCommunityBoardUseCase: DeleteCommunityBoardUseCase
): ViewModel() {
    private val _swipeRefreshLoading = MutableStateFlow(false)
    val swipeRefreshLoading = _swipeRefreshLoading.asStateFlow()

    private val _getCommunityBoardDetailResponse = MutableStateFlow<Event<GetCommunityBoardDetailResponseModel>>(Event.Loading)
    val getCommunityBoardDetailResponse = _getCommunityBoardDetailResponse.asStateFlow()

    private val _deleteCommunityBoardDetailResponse = MutableStateFlow<Event<Unit>>(Event.Loading)

    private val _getCommunityCommentResponse = MutableStateFlow<Event<List<GetCommunityCommentResponseModel>>>(Event.Loading)
    val getCommunityCommentResponse = _getCommunityCommentResponse.asStateFlow()

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

    private lateinit var _communityDetail: MutableState<GetCommunityBoardDetailResponseModel>
    val communityDetail: MutableState<GetCommunityBoardDetailResponseModel>
        get() = _communityDetail

    var communityComment = mutableStateListOf<GetCommunityCommentResponseModel>()

    internal fun getCommunityDetail(boardId: Long) = viewModelScope.launch {
        getCommunityDetailUseCase(boardId = boardId).onSuccess {
            it.catch { remoteError ->
                _getCommunityBoardDetailResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getCommunityBoardDetailResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getCommunityBoardDetailResponse.value = error.errorHandling()
        }
    }

    internal fun deleteCommunityDetail(boardId: Long)= viewModelScope.launch {
        deleteCommunityBoardUseCase(boardId = boardId).onSuccess {
            it.catch {remoteError ->
                _deleteCommunityBoardDetailResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _deleteCommunityBoardDetailResponse.value = Event.Success(Unit)
            }
        }.onFailure {error ->
            _deleteCommunityBoardDetailResponse.value = error.errorHandling()
        }
    }

    internal fun getCommunityComment(boardId: Long) = viewModelScope.launch {
        getCommunityCommentUseCase(boardId = boardId).onSuccess {
            it.catch { remoteError ->
                _getCommunityCommentResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getCommunityCommentResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getCommunityCommentResponse.value = error.errorHandling()
        }
    }
}