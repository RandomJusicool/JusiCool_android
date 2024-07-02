package com.jusiCool.domain.model.auth.request

data class PostAuthSignUpRequestModel(
    val email: String,
    val name: String,
    val password: String,
)
