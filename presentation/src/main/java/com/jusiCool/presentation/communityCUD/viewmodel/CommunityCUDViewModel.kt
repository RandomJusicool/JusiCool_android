package com.jusiCool.presentation.communityCUD.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.model.board.response.GetCommunityBoardDetailResponseModel
import com.jusiCool.domain.model.comment.request.PostWritingCommunityCommentRequestModel
import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import com.jusiCool.domain.usecase.board.DeleteCommunityBoardUseCase
import com.jusiCool.domain.usecase.board.GetCommunityDetailUseCase
import com.jusiCool.domain.usecase.board.PatchCommunityBoardUseCase
import com.jusiCool.domain.usecase.board.PostWritingCommunityUseCase
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
class CommunityCUDViewModel @Inject constructor(
    private val postWritingCommunityUseCase: PostWritingCommunityUseCase,
    private val patchCommunityBoardUseCase: PatchCommunityBoardUseCase,
    private val getCommunityDetailUseCase: GetCommunityDetailUseCase,
    private val getCommunityCommentUseCase: GetCommunityCommentUseCase,
    private val getLikeUseCase: GetLikeUseCase,
    private val deleteCommunityBoardUseCase: DeleteCommunityBoardUseCase,
    private val postLikeUseCase: PostLikeUseCase,
    private val deleteLikeUseCase: DeleteLikeUseCase,
    private val postWritingCommunityCommentUseCase: PostWritingCommunityCommentUseCase
) : ViewModel() {
    private val _postWritingCommunityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val postWritingCommunityResponse = _postWritingCommunityResponse.asStateFlow()

    private val _patchCommunityBoardResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val patchCommunityBoardResponse = _patchCommunityBoardResponse.asStateFlow()

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

    var title = mutableStateOf("")
        private set

    var content = mutableStateOf("")
        private set

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

    internal fun postWritingCommunity(
        communityId: String,
        title: String,
        content: String
    ) = viewModelScope.launch {
        postWritingCommunityUseCase(
            communityId = communityId,
            body = WritingCommunityBoardRequestModel(
                title = title,
                content = content
            )
        ).onSuccess {
            it.catch { remoteError ->
                _postWritingCommunityResponse.value = remoteError.errorHandling()
            }.collect {
                _postWritingCommunityResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _postWritingCommunityResponse.value = error.errorHandling()
        }
    }

    internal fun patchCommunityBoard(
        boardId: String,
        title: String,
        content: String
    ) = viewModelScope.launch {
        patchCommunityBoardUseCase(
            boardId = boardId,
            body = WritingCommunityBoardRequestModel(
                title = title,
                content = content
            )
        ).onSuccess {
            it.catch { remoteError ->
                _patchCommunityBoardResponse.value = remoteError.errorHandling()
            }.collect {
                _patchCommunityBoardResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _patchCommunityBoardResponse.value = error.errorHandling()
        }
    }
    internal fun getCommunityDetail(boardId: String) = viewModelScope.launch {
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

    internal fun deleteCommunityDetail(communityId: String, boardId: String) = viewModelScope.launch {
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

    internal fun getCommunityComment(boardId: String) = viewModelScope.launch {
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

    internal fun getLike(boardId: String) = viewModelScope.launch {
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


    internal fun postLike(boardId: String) = viewModelScope.launch {
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

    internal fun deleteLike(boardId: String) = viewModelScope.launch {
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
        boardId: String,
        content: String
    ) = viewModelScope.launch {
        postWritingCommunityCommentUseCase(
            boardId = boardId,
            body = PostWritingCommunityCommentRequestModel(content)
        ).onSuccess {
            it.catch { remoteError ->
                _postWritingCommunityComment.value = remoteError.errorHandling()
            }.collect {
                _postWritingCommunityComment.value = Event.Success()
            }
        }.onFailure { error ->
            _postWritingCommunityComment.value = error.errorHandling()
        }
    }

    internal fun getDetailValue() {
        title.value = communityDetail.value.title
        content.value = communityDetail.value.content
    }
}