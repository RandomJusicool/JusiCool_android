package com.jusiCool.data.remote.datesource.community

import com.jusiCool.data.remote.api.CommunityAPI
import com.jusiCool.data.remote.dto.community.response.GetCommunityListResponse
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteCommunityDataSourceImpl @Inject constructor(
    private val serviceCommunity: CommunityAPI
) : RemoteCommunityDataSource {
    override suspend fun getCommunityList(): Flow<List<GetCommunityListResponse>> =
        performApiRequest { serviceCommunity.getCommunityList() }
}