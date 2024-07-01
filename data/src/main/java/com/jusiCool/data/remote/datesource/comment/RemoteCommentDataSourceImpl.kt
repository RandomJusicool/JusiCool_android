package com.jusiCool.data.remote.datesource.comment

import com.jusiCool.data.remote.api.CommentAPI
import com.jusiCool.data.remote.dto.comment.reqeust.PostWritingCommunityCommentRequest
import com.jusiCool.data.remote.dto.comment.response.GetCommunityCommentResponse
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteCommentDataSourceImpl @Inject constructor(
    private val serviceComment: CommentAPI
) : RemoteCommentDataSource {
    override suspend fun postWritingCommunityComment(
        boardId: Long,
        body: PostWritingCommunityCommentRequest
    ): Flow<Unit> =
        performApiRequest { serviceComment.postWritingCommunityComment(
            boardId = boardId,
            body = body
        ) }

    override suspend fun getCommunityComment(boardId: Long): Flow<List<GetCommunityCommentResponse>> =
        performApiRequest { serviceComment.getCommunityComment(boardId = boardId) }
}