package com.jusiCool.domain.repository

import com.jusiCool.domain.model.email.request.PostEmailRequestModel
import kotlinx.coroutines.flow.Flow

interface EmailRepository {
    suspend fun postEmail(body: PostEmailRequestModel) : Flow<Unit>
    suspend fun getEmailVerify(email: String, authCode: String) : Flow<Unit>
}