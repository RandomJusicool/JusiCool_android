package com.jusiCool.presentation.communityWriting.viewModel

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.usecase.board.PostWritingCommunityUseCase
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.errorHandling
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommunityWritingViewModel @Inject constructor(
    private val postWritingCommunityUseCase: PostWritingCommunityUseCase
) : ViewModel() {
    private val _postWritingCommunityResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val postWritingCommunityResponse = _postWritingCommunityResponse.asStateFlow()

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
}