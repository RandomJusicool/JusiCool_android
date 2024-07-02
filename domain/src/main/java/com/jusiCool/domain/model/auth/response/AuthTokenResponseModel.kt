package com.jusiCool.domain.model.auth.response

data class AuthTokenResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresIn: String,
    val refreshTokenExpiresIn: String,
)
