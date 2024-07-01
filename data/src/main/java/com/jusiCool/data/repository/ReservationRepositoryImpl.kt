package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.reservation.RemoteReservationDataSource
import com.jusiCool.data.remote.dto.reservation.response.toModel
import com.jusiCool.domain.model.reservation.response.GetReserveStockResponseModel
import com.jusiCool.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val dataSource: RemoteReservationDataSource
) : ReservationRepository {
    override suspend fun getReservationStock(): Flow<GetReserveStockResponseModel> {
        return dataSource.getReservationStock().map { it.toModel() }
    }
}