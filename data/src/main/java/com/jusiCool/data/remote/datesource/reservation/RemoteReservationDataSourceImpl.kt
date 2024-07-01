package com.jusiCool.data.remote.datesource.reservation

import com.jusiCool.data.remote.api.ReservationAPI
import com.jusiCool.data.remote.dto.reservation.response.GetReserveStockResponse
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteReservationDataSourceImpl @Inject constructor(
    private val serviceReservation: ReservationAPI
) : RemoteReservationDataSource {
    override suspend fun getReservationStock(): Flow<GetReserveStockResponse> =
        performApiRequest { serviceReservation.getReservationStock() }
}