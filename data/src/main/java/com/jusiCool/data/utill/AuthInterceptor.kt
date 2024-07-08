package com.jusiCool.data.utill

import com.jusiCool.data.BuildConfig
import com.jusiCool.data.local.datasource.EncryptedSharedPreferencesDataSource
import com.jusiCool.data.remote.dto.auth.response.AuthTokenResponse
import com.jusiCool.domain.util.exception.NeedLoginException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val encryptedSharedPreferencesDataSource: EncryptedSharedPreferencesDataSource
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val ignorePath = listOf("api/v1/auth", "api/v1/email")
        val path = request.url.encodedPath
        val method = request.method

        if (ignorePath.contains(path) && method != "PATCH") {
            return chain.proceed(request)
        }

        runBlocking {
            val refreshTime = encryptedSharedPreferencesDataSource.getRefreshTime()
            val accessToken = encryptedSharedPreferencesDataSource.getAccessToken()
            val refreshToken = encryptedSharedPreferencesDataSource.getRefreshToken()
            if (refreshTime == null) return@runBlocking


            if (refreshToken.isNullOrEmpty()) {
                val client = OkHttpClient()
                val refreshRequest = Request.Builder()
                    .url(BuildConfig.BASE_URL + "/api/v1/auth")
                    .patch(chain.request().body ?: RequestBody.Companion.create(null, byteArrayOf()))
                    .addHeader("refreshToken", "Bearer $refreshToken")
                    .build()

                val moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<AuthTokenResponse> = moshi.adapter(AuthTokenResponse::class.java)
                val response = client.newCall(refreshRequest).execute()
                if (response.isSuccessful) {
                    val token = adapter.fromJson(response.body!!.string()) ?: throw NeedLoginException()
                    encryptedSharedPreferencesDataSource.setAccessToken(token.accessToken)
                    encryptedSharedPreferencesDataSource.setAccessTime(token.accessTokenExpiresIn)
                    encryptedSharedPreferencesDataSource.setRefreshToken(token.refreshToken)
                    encryptedSharedPreferencesDataSource.setRefreshTime(token.refreshTokenExpiresIn)
                } else throw NeedLoginException()
            } else {
                builder.addHeader("Authorization", "Bearer $accessToken")
            }
            if (method == "PATCH") {
                builder.addHeader(name = "refreshToken", value = "Bearer $refreshToken")
            }
            builder.header(name = "Authorization", value = "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }
}