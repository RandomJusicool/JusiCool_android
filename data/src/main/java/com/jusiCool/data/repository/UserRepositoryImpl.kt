package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.user.RemoteUserDataSource
import com.jusiCool.data.remote.dto.user.response.toModel
import com.jusiCool.domain.model.user.response.GetMyCommunityBoardModel
import com.jusiCool.domain.model.user.response.GetMyPointModel
import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.jusiCool.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteUserDataSource
) : UserRepository {
    override suspend fun getMyCommunityBoard(): Flow<List<GetMyCommunityBoardModel>> {
        return remoteDataSource.getMyCommunityBoard().map { list -> list.map { it.toModel() } }
    }

    override suspend fun getMyStock(): Flow<List<GetMyStockModel>> {
        return remoteDataSource.getMyStock().map { list -> list.map { it.toModel() } }
    }

    override suspend fun getMyPoint(): Flow<GetMyPointModel> {
        return remoteDataSource.getMyPoint().map { it.toModel() }
    }
}