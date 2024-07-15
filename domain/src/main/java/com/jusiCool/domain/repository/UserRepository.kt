package com.jusiCool.domain.repository

import com.jusiCool.domain.model.user.response.GetMyCommunityBoardModel
import com.jusiCool.domain.model.user.response.GetMyPointModel
import com.jusiCool.domain.model.user.response.GetMyStockModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getMyCommunityBoard(): Flow<List<GetMyCommunityBoardModel>>
    suspend fun getMyStock(): Flow<List<GetMyStockModel>>
    suspend fun getMyPoint(): Flow<GetMyPointModel>
}