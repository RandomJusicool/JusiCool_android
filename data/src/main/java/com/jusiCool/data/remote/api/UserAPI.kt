package com.jusiCool.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {

    @GET("/api/v1/user")
    suspend fun getMyStock(
        @Path("stock_code") stock_code: String
    )

    @GET("/api/v1/user/board")
    suspend fun getMyCommunityBoard()

    @GET("/api/v1/user/point")
    suspend fun getMyPoint()
}