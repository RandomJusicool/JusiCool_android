package com.jusiCool.data.remote.dto.auth.response

import com.jusiCool.domain.model.auth.response.AuthTokenResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthTokenResponse(
    @Json(name = "accessToken") val accessToken: String,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "accessTokenExpiresIn") val accessTokenExpiresIn: String,
    @Json(name = "refreshTokenExpiresIn") val refreshTokenExpiresIn: String,
)

fun AuthTokenResponse.toModel() = AuthTokenResponseModel(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExpiresIn = accessTokenExpiresIn,
    refreshTokenExpiresIn = refreshTokenExpiresIn
)