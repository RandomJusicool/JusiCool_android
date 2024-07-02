package com.jusiCool.domain.repository

import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    suspend fun getCommunityList() : Flow<List<GetCommunityListResponseModel>>
}