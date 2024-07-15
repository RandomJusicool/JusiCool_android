package com.jusiCool.data.remote.dto.user.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMyCommunityBoard(
    @Json(name = "id") val id: Long,
)
