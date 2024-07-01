package com.jusiCool.data.remote.dto.email.request

import com.jusiCool.domain.model.email.request.GetEmailVerifyRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetEmailVerifyRequest(
    @Json(name = "email") val email: String,
    @Json(name = "authCode") val authCode: String,
)

fun GetEmailVerifyRequestModel.toDto() = GetEmailVerifyRequest(
    email = email,
    authCode = authCode
)