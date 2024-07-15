package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.stock.request.BuyStockRequest
import com.jusiCool.data.remote.dto.stock.request.StockRequest
import com.jusiCool.data.remote.dto.stock.response.GetStockDetailResponse
import com.jusiCool.data.remote.dto.stock.response.GetStockListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StockAPI {
    @GET("/api/v1/stock/{stock_code}")
    suspend fun getStockDetail(
        @Path("stock_code") stockId: String,
    ) : GetStockDetailResponse

    @GET("/api/v1/stock")
    suspend fun getStockList() : GetStockListResponse

    @POST("/api/v1/stock/{stock_code}")
    suspend fun buyStock(
        @Path("stock_code") stockId: String,
        @Body body: StockRequest
    )
    @POST("/api/v1/stock/sell/{stock_code}")
    suspend fun sellStockReserve(
        @Path("stock_code") stockId: String,
        @Body body: BuyStockRequest
    )

    @POST("/api/v1/stock/buy/{stock_code}")
    suspend fun buyStockReserve(
        @Path("stock_code") stockId: String,
        @Body body: BuyStockRequest
    )

    @DELETE("/api/v1/stock/{stock_code}")
    suspend fun deleteStock(
        @Path("stock_code") stockId: String,
        @Body body: StockRequest
    )
}