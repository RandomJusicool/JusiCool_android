package com.jusiCool.data.remote.dto.auth.request

import com.jusiCool.domain.model.auth.request.PostAuthSignInRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostAuthSignInRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
)

fun PostAuthSignInRequestModel.toDto() = PostAuthSignInRequest(
    email = email,
    password = password
)