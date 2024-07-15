package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.receipt.response.GetReceipt
import retrofit2.http.GET

interface ReceiptAPI {

    @GET("/api/v1/receipt")
    suspend fun getReceipt() : List<GetReceipt>
}