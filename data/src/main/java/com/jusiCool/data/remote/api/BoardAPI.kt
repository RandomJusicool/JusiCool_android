package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.board.request.WritingCommunityBoardRequest
import com.jusiCool.data.remote.dto.board.response.*
import retrofit2.http.*

interface BoardAPI {
    @GET("/api/v1/board/list/{community_id}")
    suspend fun getCommunityBoardList(
        @Path("community_id") communityId: Long
    ) : List<GetCommunityBoardListResponse>

    @GET("/api/v1/board/{board_id}")
    suspend fun getCommunityDetail(
        @Path("board_id") boardId: Long
    ) : GetCommunityBoardDetailResponse

    @POST("/api/v1/board/{community_id}")
    suspend fun postWritingCommunity(
        @Path("community_id") communityId: Long,
        @Body body: WritingCommunityBoardRequest
    )

    @PATCH("/api/v1/board/{board_id}")
    suspend fun patchBoardCommunity(
        @Path("board_id") boardId: Long,
        @Body body: WritingCommunityBoardRequest
    )

    @DELETE("/api/v1/board/{board_id}")
    suspend fun deleteBoardCommunity(
        @Path("community_id") communityId: Long,
        @Path("board_id") boardId: Long
    )
}