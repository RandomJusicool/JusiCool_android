package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.user.response.GetMyCommunityBoard
import com.jusiCool.data.remote.dto.user.response.GetMyPoint
import com.jusiCool.data.remote.dto.user.response.GetMyStock
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {

    @GET("/api/v1/user")
    suspend fun getMyStock() : List<GetMyStock>

    @GET("/api/v1/user/board")
    suspend fun getMyCommunityBoard() : List<GetMyCommunityBoard>

    @GET("/api/v1/user/point")
    suspend fun getMyPoint() : GetMyPoint
}