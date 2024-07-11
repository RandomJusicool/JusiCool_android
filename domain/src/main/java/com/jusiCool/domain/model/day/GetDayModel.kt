package com.jusiCool.domain.model.day

data class GetDayModel(
    val marketPrice: Long, // 시가
    val highPrice: Long, // 고가
    val lowPrice: Long, // 저가
    val presentPrice: Long, // 현재가
    val headPrice: Long, // 종가
    val volume: Long, // 거래량
    val upDownPercent: Long, // 등락률
    val storeAt: String // 저장된 시간
)