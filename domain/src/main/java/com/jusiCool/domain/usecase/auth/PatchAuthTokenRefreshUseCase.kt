package com.jusiCool.domain.usecase.auth

import com.jusiCool.domain.repository.AuthRepository
import javax.inject.Inject

class PatchAuthTokenRefreshUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(refreshToken: String) = runCatching {
        repository.patchAuthTokenRefresh(refreshToken = refreshToken)
    }
}