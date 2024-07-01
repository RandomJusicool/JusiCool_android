package com.jusiCool.domain.usecase.email

import com.jusiCool.domain.model.email.request.PostEmailRequestModel
import com.jusiCool.domain.repository.EmailRepository
import javax.inject.Inject

class PostEmailUseCase @Inject constructor(
    private val repository: EmailRepository
) {
    suspend operator fun invoke(body: PostEmailRequestModel) = runCatching {
        repository.postEmail(body = body)
    }
}