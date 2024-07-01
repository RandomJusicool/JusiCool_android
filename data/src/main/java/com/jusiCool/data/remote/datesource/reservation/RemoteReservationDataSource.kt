package com.jusiCool.data.remote.datesource.reservation

import com.jusiCool.data.remote.dto.reservation.response.GetReserveStockResponse
import kotlinx.coroutines.flow.Flow

interface RemoteReservationDataSource {
    suspend fun getReservationStock() : Flow<GetReserveStockResponse>
}