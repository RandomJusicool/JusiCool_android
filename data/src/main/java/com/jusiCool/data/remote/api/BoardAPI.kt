package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.board.request.WritingCommunityBoardRequest
import com.jusiCool.data.remote.dto.board.response.*
import retrofit2.http.*

interface BoardAPI {
    @GET("/api/v1/board/list")
    suspend fun getCommunityList(
        @Path("community_id") communityId: Long
    ) : List<GetCommunityBoardListResponse>

    @GET("/api/v1/board")
    suspend fun getCommunityDetail(
        @Path("board_id") boardId: Long
    ) : GetCommunityBoardDetailResponse

    @POST("/api/v1/board")
    suspend fun postWritingCommunity(
        @Path("community_id") communityId: Long,
        @Body body: WritingCommunityBoardRequest
    )

    @PATCH("/api/v1/board")
    suspend fun patchBoardCommunity(
        @Path("board_id") boardId: Long,
        @Body body: WritingCommunityBoardRequest
    )

    @DELETE("/api/v1/board")
    suspend fun deleteBoardCommunity(
        @Path("board_id") boardId: Long
    )
}