package com.jusiCool.presentation.communityDetail.viewModel

import android.util.Log
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
import com.jusiCool.domain.model.comment.request.PostWritingCommunityCommentRequestModel
import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import com.jusiCool.domain.usecase.board.DeleteCommunityBoardUseCase
import com.jusiCool.domain.usecase.board.GetCommunityDetailUseCase
import com.jusiCool.domain.usecase.board.PatchCommunityBoardUseCase
import com.jusiCool.domain.usecase.comment.GetCommunityCommentUseCase
import com.jusiCool.domain.usecase.comment.PostWritingCommunityCommentUseCase
import com.jusiCool.domain.usecase.like.DeleteLikeUseCase
import com.jusiCool.domain.usecase.like.GetLikeUseCase
import com.jusiCool.domain.usecase.like.PostLikeUseCase
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
    private val getLikeUseCase: GetLikeUseCase,
    private val deleteCommunityBoardUseCase: DeleteCommunityBoardUseCase,
    private val postLikeUseCase: PostLikeUseCase,
    private val deleteLikeUseCase: DeleteLikeUseCase,
    private val postWritingCommunityCommentUseCase: PostWritingCommunityCommentUseCase
) : ViewModel() {
    private val _swipeRefreshLoading = MutableStateFlow(false)
    val swipeRefreshLoading = _swipeRefreshLoading.asStateFlow()

    private val _getCommunityBoardDetailResponse = MutableStateFlow<Event<GetCommunityBoardDetailResponseModel>>(Event.Loading)
    val getCommunityBoardDetailResponse = _getCommunityBoardDetailResponse.asStateFlow()

    private val _deleteCommunityBoardDetailResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deleteCommunityBoardDetailResponse = _deleteCommunityBoardDetailResponse.asStateFlow()

    private val _getCommunityCommentResponse = MutableStateFlow<Event<List<GetCommunityCommentResponseModel>>>(Event.Loading)
    val getCommunityCommentResponse = _getCommunityCommentResponse.asStateFlow()

    private val _getLikeResponse = MutableStateFlow<Event<Boolean>>(Event.Loading)
    val getLikeResponse = _getLikeResponse.asStateFlow()

    private val _postLikeResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val postLikeResponse = _postLikeResponse.asStateFlow()

    private val _deleteLikeResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deleteLikeResponse = _deleteLikeResponse.asStateFlow()

    private val _postWritingCommunityComment = MutableStateFlow<Event<Unit>>(Event.Loading)
    val postWritingCommunityComment = _postWritingCommunityComment.asStateFlow()

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

    var communityDetail = mutableStateOf(
        GetCommunityBoardDetailResponseModel(
            communityName = "",
            title = "",
            content = "",
            likes = 0
        )
    )
        private set

    var communityComment = mutableStateListOf<GetCommunityCommentResponseModel>()
        private set

    var like = mutableStateOf(false)
        private set

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

    internal fun deleteCommunityDetail(communityId: Long, boardId: Long) = viewModelScope.launch {
        deleteCommunityBoardUseCase(communityId = communityId, boardId = boardId).onSuccess {
            it.catch { remoteError ->
                _deleteCommunityBoardDetailResponse.value = remoteError.errorHandling()
            }.collect {
                _deleteCommunityBoardDetailResponse.value = Event.Success()
            }
        }.onFailure { error ->
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

    internal fun getLike(boardId: Long) = viewModelScope.launch {
        getLikeUseCase(boardId = boardId).onSuccess {
            it.catch { remoteError ->
                _getLikeResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getLikeResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getLikeResponse.value = error.errorHandling()
        }
    }


    internal fun postLike(boardId: Long) = viewModelScope.launch {
        postLikeUseCase(boardId = boardId).onSuccess {
            it.catch { remoteError ->
                _postLikeResponse.value = remoteError.errorHandling()
            }.collect {
                _postLikeResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _postLikeResponse.value = error.errorHandling()
        }
    }

    internal fun deleteLike(boardId: Long) = viewModelScope.launch {
        deleteLikeUseCase(boardId = boardId).onSuccess {
            it.catch { remoteError ->
                _deleteLikeResponse.value = remoteError.errorHandling()
            }.collect {
                _deleteLikeResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _deleteLikeResponse.value = error.errorHandling()
        }
    }

    internal fun postWritingCommunityComment(
        boardId: Long,
        content: String
    ) = viewModelScope.launch {
        postWritingCommunityCommentUseCase(boardId = boardId, body = PostWritingCommunityCommentRequestModel(content)).onSuccess {
            it.catch { remoteError ->
                _postWritingCommunityComment.value = remoteError.errorHandling()
            }.collect {
                _postWritingCommunityComment.value = Event.Success()
            }
        }.onFailure { error ->
            _postWritingCommunityComment.value = error.errorHandling()
        }
    }
}