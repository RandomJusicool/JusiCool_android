package com.jusiCool.domain.model.email.request

data class GetEmailVerifyRequestModel(
    val email: String,
    val authCode: String,
)
