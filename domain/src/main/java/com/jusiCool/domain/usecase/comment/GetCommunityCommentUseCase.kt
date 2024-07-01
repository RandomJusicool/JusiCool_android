package com.jusiCool.domain.usecase.comment

import com.jusiCool.domain.repository.CommentRepository
import javax.inject.Inject

class GetCommunityCommentUseCase @Inject constructor(
    private val repository: CommentRepository
) {
    suspend operator fun invoke(boardId: Long) = runCatching {
        repository.getCommunityComment(boardId = boardId)
    }
}