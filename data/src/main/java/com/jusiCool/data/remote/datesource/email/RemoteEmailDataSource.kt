package com.jusiCool.data.remote.datesource.email

import com.jusiCool.data.remote.dto.email.request.PostEmailRequest
import kotlinx.coroutines.flow.Flow

interface RemoteEmailDataSource {
    suspend fun postEmail(body: PostEmailRequest) : Flow<Unit>
    suspend fun getEmailVerify(email: String, authCode: String) : Flow<Unit>
}
