package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.comment.RemoteCommentDataSource
import com.jusiCool.data.remote.dto.comment.reqeust.toDto
import com.jusiCool.data.remote.dto.comment.response.toModel
import com.jusiCool.domain.model.comment.request.PostWritingCommunityCommentRequestModel
import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import com.jusiCool.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val dataSource: RemoteCommentDataSource
) : CommentRepository {
    override suspend fun postWritingCommunityComment(
        boardId: Long,
        body: PostWritingCommunityCommentRequestModel
    ): Flow<Unit> {
        return dataSource.postWritingCommunityComment(
            boardId = boardId,
            body = body.toDto()
        )
    }

    override suspend fun getCommunityComment(boardId: Long): Flow<List<GetCommunityCommentResponseModel>> {
        return dataSource.getCommunityComment(boardId = boardId).map { list -> list.map { it.toModel() } }
    }

}