package com.jusiCool.data.remote.datesource.news

import com.jusiCool.data.remote.dto.news.reponse.ApiResponse
import kotlinx.coroutines.flow.Flow

interface RemoteNewsDataSource {
    suspend fun getNews() : Flow<ApiResponse>
}