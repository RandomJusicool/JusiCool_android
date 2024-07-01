package com.jusiCool.domain.repository

import com.jusiCool.domain.model.reservation.response.GetReserveStockResponseModel
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    suspend fun getReservationStock() : Flow<GetReserveStockResponseModel>
}