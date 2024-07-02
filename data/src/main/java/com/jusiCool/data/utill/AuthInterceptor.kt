package com.jusiCool.data.utill

import android.util.Log
import com.jusiCool.data.local.datasource.AuthTokenDataSource
import com.jusiCool.data.remote.dto.auth.response.AuthTokenResponse
import com.jusiCool.domain.util.exception.NeedLoginException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val localDataSource: AuthTokenDataSource
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val currentTime = System.currentTimeMillis().toJusiCoolDate()
        val ignorePath = listOf("/auth", "/email")
        val path = request.url.encodedPath
        val method = request.method

        if (ignorePath.contains(path) && method != "PATCH") {
            return chain.proceed(request)
        }

        runBlocking {
            val refreshTime = localDataSource.getRefreshTokenExp().first()
            val accessTime = localDataSource.getAccessTokenExp().first()
            val accessToken = localDataSource.getAccessToken().first()
            val refreshToken = localDataSource.getRefreshToken().first()

            if (refreshTime == "") return@runBlocking

            if (currentTime.after(refreshTime.toDate())) throw NeedLoginException()

            if (currentTime.after(accessTime.toDate())) {
                val client = OkHttpClient()
                val refreshRequest = Request.Builder()
                    .url("adfasdfasdfasdfa" + "/api/v1/auth") // BaseUrl 추후 작성
                    .patch(chain.request().body ?: RequestBody.Companion.create(null, byteArrayOf()))
                    .addHeader("refreshToken", "Bearer $refreshToken")
                    .build()

                val moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<AuthTokenResponse> = moshi.adapter(AuthTokenResponse::class.java)
                val response = client.newCall(refreshRequest).execute()
                if (response.isSuccessful) {
                    val token = adapter.fromJson(response.body!!.string()) ?: throw NeedLoginException()
                    localDataSource.setAccessToken(token.accessToken)
                    localDataSource.setAccessTokenExp(token.accessTokenExpiresIn)
                    localDataSource.setRefreshToken(token.refreshToken)
                    localDataSource.setRefreshTokenExp(token.refreshTokenExpiresIn)
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