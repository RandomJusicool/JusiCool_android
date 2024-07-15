package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.receipt.response.GetReceipt
import retrofit2.http.GET
import retrofit2.http.Query

interface ReceiptAPI {

    @GET("/api/v1/receipt")
    suspend fun getReceipt(
        @Query("status") status: String
    ) : List<GetReceipt>
}