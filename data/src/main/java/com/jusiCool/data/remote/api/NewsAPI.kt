package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.news.reponse.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/v1/articles")
    suspend fun getNews(
        @Query("symbols") symbols: String?,
        @Query("keyword") keyword: String?,
        @Query("company_name") companyName: String?,
        @Query("date_from") dateFrom: String?,
        @Query("date_to") dateTo: String?,
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int?,
        @Query("api_key") apiKey: String
    ) : ApiResponse
}