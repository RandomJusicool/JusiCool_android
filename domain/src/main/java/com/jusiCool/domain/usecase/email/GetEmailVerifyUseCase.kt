package com.jusiCool.domain.usecase.email

import com.jusiCool.domain.model.email.request.GetEmailVerifyRequestModel
import com.jusiCool.domain.repository.EmailRepository
import javax.inject.Inject

class GetEmailVerifyUseCase @Inject constructor(
    private val repository: EmailRepository
) {
    suspend operator fun invoke(body: GetEmailVerifyRequestModel) = runCatching {
        repository.getEmailVerify(body = body)
    }
}