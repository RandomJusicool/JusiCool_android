package com.jusiCool.data.remote.datesource.comment

import com.jusiCool.data.remote.dto.comment.reqeust.PostWritingCommunityCommentRequest
import com.jusiCool.data.remote.dto.comment.response.GetCommunityCommentResponse
import kotlinx.coroutines.flow.Flow

interface RemoteCommentDataSource {
    suspend fun postWritingCommunityComment(boardId: Long, body: PostWritingCommunityCommentRequest) : Flow<Unit>
    suspend fun getCommunityComment(boardId: Long) : Flow<List<GetCommunityCommentResponse>>
}