package com.jusiCool.domain.model.user.response

data class GetMyPointModel(
    val points: Long,
    val upDownPercent: Double,
    val upDownPoints: Long,
)
