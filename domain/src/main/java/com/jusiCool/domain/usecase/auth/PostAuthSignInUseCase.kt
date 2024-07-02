package com.jusiCool.domain.usecase.auth

import com.jusiCool.domain.model.auth.request.PostAuthSignInRequestModel
import com.jusiCool.domain.repository.AuthRepository
import javax.inject.Inject

class PostAuthSignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(body: PostAuthSignInRequestModel) = runCatching {
        repository.authSignIn(body = body)
    }
}