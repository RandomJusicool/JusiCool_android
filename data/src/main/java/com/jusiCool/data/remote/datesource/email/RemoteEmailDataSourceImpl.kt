package com.jusiCool.data.remote.datesource.email

import com.jusiCool.data.remote.api.EmailAPI
import com.jusiCool.data.remote.dto.email.request.GetEmailVerifyRequest
import com.jusiCool.data.remote.dto.email.request.PostEmailRequest
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteEmailDataSourceImpl @Inject constructor(
    private val serviceEmail: EmailAPI
) : RemoteEmailDataSource {
    override suspend fun postEmail(body: PostEmailRequest): Flow<Unit> =
        performApiRequest { serviceEmail.postEmail(body = body) }

    override suspend fun getEmailVerify(body: GetEmailVerifyRequest): Flow<Unit> =
        performApiRequest { serviceEmail.getEmailVerify(body = body) }
}