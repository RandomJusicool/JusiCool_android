package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.stock.request.BuyStockRequest
import com.jusiCool.data.remote.dto.stock.request.StockRequest
import com.jusiCool.data.remote.dto.stock.response.GetStockDetailResponse
import com.jusiCool.data.remote.dto.stock.response.GetStockListResponse
import com.jusiCool.domain.model.stock.request.StockRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StockAPI {
    @GET("/api/v1/stock")
    suspend fun getStockDetail(
        @Path("stock_id") stockId: Long,
    ) : GetStockDetailResponse

    @GET("/api/v1/stock")
    suspend fun getStockList() : GetStockListResponse

    @POST("/api/v1/stock")
    suspend fun buyStock(
        @Path("stock_id") stockId: Long,
        @Body body: StockRequest
    )
    @POST("/api/v1/stock/sell")
    suspend fun sellStockReserve(
        @Path("stock_id") stockId: Long,
        @Body body: BuyStockRequest
    )

    @POST("/api/v1/stock/buy")
    suspend fun buyStockReserve(
        @Path("stock_id") stockId: Long,
        @Body body: BuyStockRequest
    )
}