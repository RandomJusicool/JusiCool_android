package com.jusiCool.data.remote.datesource.community

import com.jusiCool.data.remote.dto.community.response.GetCommunityListResponse
import kotlinx.coroutines.flow.Flow

interface RemoteCommunityDataSource {
    suspend fun getCommunityList() : Flow<List<GetCommunityListResponse>>
}