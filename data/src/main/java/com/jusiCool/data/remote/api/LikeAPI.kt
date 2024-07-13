package com.jusiCool.data.remote.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeAPI {

    @POST("/api/v1/like/{board_id}")
    suspend fun postLike(
        @Path("board_id") boardId: Long
    )

    @DELETE("/api/v1/like/{board_id}")
    suspend fun deleteLike(
        @Path("board_id") boardId: Long
    )

    @GET("/api/v1/like/{board_id}")
    suspend fun getLike(
        @Path("board_id") boardId: Long
    ): Boolean
}