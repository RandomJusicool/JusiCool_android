package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.community.response.GetCommunityListResponse
import retrofit2.http.GET

interface CommunityAPI {
    @GET("/api/v1/community")
    suspend fun getCommunityList() : List<GetCommunityListResponse>
}