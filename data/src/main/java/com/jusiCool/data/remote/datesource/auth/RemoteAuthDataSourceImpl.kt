package com.jusiCool.data.remote.datesource.auth

import com.jusiCool.data.remote.api.AuthAPI
import com.jusiCool.data.remote.dto.auth.request.PostAuthSignInRequest
import com.jusiCool.data.remote.dto.auth.request.PostAuthSignUpRequest
import com.jusiCool.data.remote.dto.auth.response.AuthTokenResponse
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val serviceAuth: AuthAPI
) : RemoteAuthDataSource {
    override suspend fun authSignUp(body: PostAuthSignUpRequest): Flow<Unit> =
        performApiRequest { serviceAuth.postAuthSignUp(body = body) }

    override suspend fun authSignIn(body: PostAuthSignInRequest): Flow<AuthTokenResponse> =
        performApiRequest { serviceAuth.postAuthSignIn(body = body) }

    override suspend fun patchAuthTokenRefresh(): Flow<AuthTokenResponse> =
        performApiRequest { serviceAuth.patchAuthTokenRefresh() }

    override suspend fun deleteAuth(): Flow<Unit> =
        performApiRequest { serviceAuth.deleteAuth() }
}