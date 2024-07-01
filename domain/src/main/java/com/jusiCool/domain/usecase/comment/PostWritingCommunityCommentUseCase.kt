package com.jusiCool.domain.usecase.comment

import com.jusiCool.domain.model.comment.request.PostWritingCommunityCommentRequestModel
import com.jusiCool.domain.repository.CommentRepository
import javax.inject.Inject

class PostWritingCommunityCommentUseCase @Inject constructor(
    private val repository: CommentRepository
) {
    suspend operator fun invoke(
        boardId: Long,
        body: PostWritingCommunityCommentRequestModel
    ) = runCatching {
        repository.postWritingCommunityComment(
            boardId = boardId,
            body = body
        )
    }
}