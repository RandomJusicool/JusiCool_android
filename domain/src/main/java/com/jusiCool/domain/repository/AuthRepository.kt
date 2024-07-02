package com.jusiCool.domain.repository

import com.jusiCool.domain.model.auth.request.PostAuthSignInRequestModel
import com.jusiCool.domain.model.auth.request.PostAuthSignUpRequestModel
import com.jusiCool.domain.model.auth.response.AuthTokenResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authSignUp(body: PostAuthSignUpRequestModel) : Flow<Unit>
    suspend fun authSignIn(body: PostAuthSignInRequestModel) : Flow<AuthTokenResponseModel>
    suspend fun patchAuthTokenRefresh() : Flow<AuthTokenResponseModel>
    suspend fun deleteAuth() : Flow<Unit>
}