package com.jusiCool.data.remote.datesource.user

import com.jusiCool.data.remote.api.UserAPI
import com.jusiCool.data.remote.dto.user.response.GetMyCommunityBoard
import com.jusiCool.data.remote.dto.user.response.GetMyPoint
import com.jusiCool.data.remote.dto.user.response.GetMyStock
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val serviceUser: UserAPI,
) : RemoteUserDataSource {
    override suspend fun getMyCommunityBoard(): Flow<List<GetMyCommunityBoard>> =
        performApiRequest { serviceUser.getMyCommunityBoard() }

    override suspend fun getMyStock(stockCode: String): Flow<List<GetMyStock>> =
        performApiRequest { serviceUser.getMyStock(stockCode = stockCode) }

    override suspend fun getMyPoint(): Flow<GetMyPoint> =
        performApiRequest { serviceUser.getMyPoint() }
}