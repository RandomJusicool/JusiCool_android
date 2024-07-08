package com.jusiCool.data.local.datasource

interface EncryptedSharedPreferencesDataSource {
    fun getAccessToken(): String?
    fun setAccessToken(accessToken: String)
    fun deleteAccessToken()

    fun getAccessTime(): String?
    fun setAccessTime(accessTime: String)
    fun deleteAccessTime()

    fun getRefreshToken(): String?
    fun setRefreshToken(refreshToken: String)
    fun deleteRefreshToken()

    fun getRefreshTime(): String?
    fun setRefreshTime(refreshTime: String)
    fun deleteRefreshTime()
}