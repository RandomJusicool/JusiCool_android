package com.jusiCool.data.remote.datesource.auth

import com.jusiCool.data.remote.dto.auth.request.PostAuthSignInRequest
import com.jusiCool.data.remote.dto.auth.request.PostAuthSignUpRequest
import com.jusiCool.data.remote.dto.auth.response.AuthTokenResponse
import kotlinx.coroutines.flow.Flow

interface RemoteAuthDataSource {
    suspend fun authSignUp(body: PostAuthSignUpRequest) : Flow<Unit>
    suspend fun authSignIn(body: PostAuthSignInRequest) : Flow<AuthTokenResponse>
    suspend fun patchAuthTokenRefresh() : Flow<AuthTokenResponse>
    suspend fun deleteAuth() : Flow<Unit>
}