package com.jusiCool.data.remote.dto.email.request

import com.jusiCool.domain.model.email.request.PostEmailRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostEmailRequest(
    @Json(name = "email") val email: String,
)

fun PostEmailRequestModel.toDto() = PostEmailRequest(email = email)