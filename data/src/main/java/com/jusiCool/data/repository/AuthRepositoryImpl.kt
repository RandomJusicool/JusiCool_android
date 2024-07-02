package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.auth.RemoteAuthDataSource
import com.jusiCool.data.remote.dto.auth.request.toDto
import com.jusiCool.data.remote.dto.auth.response.toModel
import com.jusiCool.domain.model.auth.request.PostAuthSignInRequestModel
import com.jusiCool.domain.model.auth.request.PostAuthSignUpRequestModel
import com.jusiCool.domain.model.auth.response.AuthTokenResponseModel
import com.jusiCool.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: RemoteAuthDataSource
) : AuthRepository {
    override suspend fun authSignUp(body: PostAuthSignUpRequestModel): Flow<Unit> {
        return dataSource.authSignUp(body = body.toDto())
    }

    override suspend fun authSignIn(body: PostAuthSignInRequestModel): Flow<AuthTokenResponseModel> {
        return dataSource.authSignIn(body = body.toDto()).map { it.toModel() }
    }

    override suspend fun patchAuthTokenRefresh(): Flow<AuthTokenResponseModel> {
        return dataSource.patchAuthTokenRefresh().map { it.toModel() }
    }

    override suspend fun deleteAuth(): Flow<Unit> {
        return dataSource.deleteAuth()
    }
}