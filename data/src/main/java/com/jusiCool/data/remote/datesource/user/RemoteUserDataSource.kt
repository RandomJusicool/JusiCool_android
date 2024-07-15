package com.jusiCool.data.remote.datesource.user

import com.jusiCool.data.remote.dto.user.response.GetMyCommunityBoard
import com.jusiCool.data.remote.dto.user.response.GetMyPoint
import com.jusiCool.data.remote.dto.user.response.GetMyStock
import kotlinx.coroutines.flow.Flow

interface RemoteUserDataSource {
    suspend fun getMyCommunityBoard(): Flow<List<GetMyCommunityBoard>>
    suspend fun getMyStock(stockCode: String): Flow<List<GetMyStock>>
    suspend fun getMyPoint(): Flow<GetMyPoint>
}