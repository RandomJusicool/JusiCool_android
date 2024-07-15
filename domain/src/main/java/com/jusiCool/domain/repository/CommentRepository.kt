package com.jusiCool.domain.repository

import com.jusiCool.domain.model.comment.request.PostWritingCommunityCommentRequestModel
import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    suspend fun postWritingCommunityComment(boardId: String, body: PostWritingCommunityCommentRequestModel) : Flow<Unit>
    suspend fun getCommunityComment(boardId: String) : Flow<List<GetCommunityCommentResponseModel>>
}