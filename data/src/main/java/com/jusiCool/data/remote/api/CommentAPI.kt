package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.comment.reqeust.PostWritingCommunityCommentRequest
import com.jusiCool.data.remote.dto.comment.response.GetCommunityCommentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentAPI {
    @POST("/api/v1/comment ")
    suspend fun postWritingCommunityComment(
        @Path("board_id") boardId: Long,
        @Body body: PostWritingCommunityCommentRequest
    )

    @GET("/api/v1/comment")
    suspend fun getCommunityComment(
        @Path("board_id") boardId: Long
    ) : List<GetCommunityCommentResponse>
}