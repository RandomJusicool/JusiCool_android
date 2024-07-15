package com.jusiCool.data.remote.dto.user.response

import com.jusiCool.domain.model.user.response.GetMyCommunityBoardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMyCommunityBoard(
    @Json(name = "id") val id: Long,
)

fun GetMyCommunityBoard.toModel() = GetMyCommunityBoardModel(
    id = id
)