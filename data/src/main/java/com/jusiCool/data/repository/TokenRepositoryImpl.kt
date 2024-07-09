package com.jusiCool.data.repository

import com.jusiCool.data.local.datasource.EncryptedSharedPreferencesDataSource
import com.jusiCool.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val encryptedSharedPreferencesDataSource: EncryptedSharedPreferencesDataSource
) : TokenRepository {
    override fun getAccessToken(): String? = encryptedSharedPreferencesDataSource.getAccessToken()

    override fun setAccessToken(accessToken: String) = encryptedSharedPreferencesDataSource.setAccessToken(accessToken)

    override fun deleteAccessToken() = encryptedSharedPreferencesDataSource.deleteAccessToken()

    override fun getAccessTime(): String? =encryptedSharedPreferencesDataSource.getAccessTime()

    override fun setAccessTime(accessTime: String) = encryptedSharedPreferencesDataSource.setAccessTime(accessTime)

    override fun deleteAccessTime() = encryptedSharedPreferencesDataSource.deleteAccessTime()

    override fun getRefreshToken(): String? = encryptedSharedPreferencesDataSource.getRefreshToken()

    override fun setRefreshToken(refreshToken: String) = encryptedSharedPreferencesDataSource.setRefreshToken(refreshToken)

    override fun deleteRefreshToken() = encryptedSharedPreferencesDataSource.deleteRefreshToken()

    override fun getRefreshTime(): String? = encryptedSharedPreferencesDataSource.getRefreshTime()

    override fun setRefreshTime(refreshTime: String) = encryptedSharedPreferencesDataSource.setRefreshTime(refreshTime)

    override fun deleteRefreshTime() = encryptedSharedPreferencesDataSource.deleteRefreshTime()

}