package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.email.request.GetEmailVerifyRequest
import com.jusiCool.data.remote.dto.email.request.PostEmailRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EmailAPI {
    @POST("/api/v1/email")
    suspend fun postEmail(
        @Body body: PostEmailRequest
    )

    @GET("/api/v1/email")
    suspend fun getEmailVerify(
        @Body body: GetEmailVerifyRequest
    )
}