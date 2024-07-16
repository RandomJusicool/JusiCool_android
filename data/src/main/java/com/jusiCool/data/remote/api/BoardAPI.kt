package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.board.request.WritingCommunityBoardRequest
import com.jusiCool.data.remote.dto.board.response.*
import retrofit2.http.*

interface BoardAPI {
    @GET("/api/v1/board/list/{community_id}")
    suspend fun getCommunityBoardList(
        @Path("community_id") communityId: String
    ) : List<GetCommunityBoardListResponse>

    @GET("/api/v1/board/{board_id}")
    suspend fun getCommunityDetail(
        @Path("board_id") boardId: String
    ) : GetCommunityBoardDetailResponse

    @POST("/api/v1/board/{community_id}")
    suspend fun postWritingCommunity(
        @Path("community_id") communityId: String,
        @Body body: WritingCommunityBoardRequest
    )

    @PATCH("/api/v1/board/{board_id}")
    suspend fun patchBoardCommunity(
        @Path("board_id") boardId: String,
        @Body body: WritingCommunityBoardRequest
    )

    @DELETE("/api/v1/board/{community_id}/{board_id}")
    suspend fun deleteBoardCommunity(
        @Path("community_id") communityId: String,
        @Path("board_id") boardId: String
    )
}