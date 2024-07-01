package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.email.RemoteEmailDataSource
import com.jusiCool.data.remote.dto.email.request.toDto
import com.jusiCool.domain.model.email.request.GetEmailVerifyRequestModel
import com.jusiCool.domain.model.email.request.PostEmailRequestModel
import com.jusiCool.domain.repository.EmailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val dataSource: RemoteEmailDataSource
) : EmailRepository {
    override suspend fun postEmail(body: PostEmailRequestModel): Flow<Unit> {
        return dataSource.postEmail(body = body.toDto())
    }

    override suspend fun getEmailVerify(body: GetEmailVerifyRequestModel): Flow<Unit> {
        return dataSource.getEmailVerify(body = body.toDto())
    }
}