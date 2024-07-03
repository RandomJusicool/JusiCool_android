package com.jusiCool.domain.usecase.email

import com.jusiCool.domain.repository.EmailRepository
import javax.inject.Inject

class GetEmailVerifyUseCase @Inject constructor(
    private val repository: EmailRepository
) {
    suspend operator fun invoke(email: String, authCode: String) = runCatching {
        repository.getEmailVerify(
            email = email,
            authCode = authCode
        )
    }
}