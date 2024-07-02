package com.jusiCool.data.remote.api

import com.jusiCool.data.remote.dto.auth.request.PostAuthSignInRequest
import com.jusiCool.data.remote.dto.auth.request.PostAuthSignUpRequest
import com.jusiCool.data.remote.dto.auth.response.AuthTokenResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthAPI {
    @POST("/api/v1/auth/singup")
    suspend fun postAuthSignUp(
        @Body body: PostAuthSignUpRequest
    )

    @POST("/api/v1/auth/signin")
    suspend fun postAuthSignIn(
        @Body body: PostAuthSignInRequest
    ) : AuthTokenResponse

    @PATCH("/api/v1/auth")
    suspend fun patchAuthTokenRefresh() : AuthTokenResponse

    @DELETE("/api/v1/auth")
    suspend fun deleteAuth()
}