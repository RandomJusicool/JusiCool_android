package com.jusiCool.presentation.communityCU.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.usecase.board.PatchCommunityBoardUseCase
import com.jusiCool.domain.usecase.board.PostWritingCommunityUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityWritingViewModel @Inject constructor(
    private val postWritingCommunityUseCase: PostWritingCommunityUseCase,
    private val patchCommunityBoardUseCase: PatchCommunityBoardUseCase
) : ViewModel() {
    private val _postWritingCommunityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val postWritingCommunityResponse = _postWritingCommunityResponse.asStateFlow()

    private val _patchCommunityBoardResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val patchCommunityBoardResponse = _patchCommunityBoardResponse.asStateFlow()

    var title = mutableStateOf("")
        private set

    var content = mutableStateOf("")
        private set

    internal fun postWritingCommunity(
        communityId: Long,
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
        boardId: Long,
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
}