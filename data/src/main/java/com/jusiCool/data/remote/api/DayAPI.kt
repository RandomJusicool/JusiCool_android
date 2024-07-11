package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.day.GetDayResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface DayAPI {
    @GET("/api/v1/day/{stock_code}")
    suspend fun getGraphData(
        @Path("stock_code") stockCode: String
    ): List<GetDayResponse>
}