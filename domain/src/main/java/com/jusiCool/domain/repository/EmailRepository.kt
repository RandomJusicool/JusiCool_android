package com.jusiCool.domain.repository

import com.jusiCool.domain.model.email.request.GetEmailVerifyRequestModel
import com.jusiCool.domain.model.email.request.PostEmailRequestModel
import kotlinx.coroutines.flow.Flow

interface EmailRepository {
    suspend fun postEmail(body: PostEmailRequestModel) : Flow<Unit>
    suspend fun getEmailVerify(body: GetEmailVerifyRequestModel) : Flow<Unit>
}