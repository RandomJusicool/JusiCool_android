package com.jusiCool.domain.usecase.auth

import com.jusiCool.domain.model.auth.request.PostAuthSignUpRequestModel
import com.jusiCool.domain.repository.AuthRepository
import javax.inject.Inject

class PostAuthSignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(body: PostAuthSignUpRequestModel) = runCatching {
        repository.authSignUp(body = body)
    }
}