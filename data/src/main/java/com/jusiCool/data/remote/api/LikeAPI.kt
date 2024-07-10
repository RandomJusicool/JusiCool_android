package com.jusiCool.data.remote.api

import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeAPI {

    @POST("/api/v1/like")
    suspend fun postLike(
        @Path("board_id") boardId: Long
    )

    @DELETE("/api/v1/like")
    suspend fun deleteLike(
        @Path("board_id") boardId: Long
    )
}