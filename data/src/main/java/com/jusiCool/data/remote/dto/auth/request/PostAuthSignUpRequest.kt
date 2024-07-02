package com.jusiCool.data.remote.dto.auth.request

import com.jusiCool.domain.model.auth.request.PostAuthSignUpRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostAuthSignUpRequest(
    @Json(name = "email") val email: String,
    @Json(name = "name") val name: String,
    @Json(name = "password") val password: String,
)

fun PostAuthSignUpRequestModel.toDto() = PostAuthSignUpRequest(
    email = email,
    name = name,
    password = password
)