package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.community.RemoteCommunityDataSource
import com.jusiCool.data.remote.dto.community.response.toModel
import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel
import com.jusiCool.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val dataSource: RemoteCommunityDataSource
) : CommunityRepository {
    override suspend fun getCommunityList(): Flow<List<GetCommunityListResponseModel>> {
        return dataSource.getCommunityList().map { list -> list.map { it.toModel() } }
    }
}