package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.reservation.response.GetReserveStockResponse
import retrofit2.http.GET

interface ReservationAPI {
    @GET("/api/v1/reservation")
    suspend fun getReservationStock() : GetReserveStockResponse
}