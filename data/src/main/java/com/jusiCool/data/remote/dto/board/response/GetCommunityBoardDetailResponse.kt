package com.jusiCool.data.remote.dto.board.response

import com.jusiCool.domain.model.board.response.GetCommunityBoardDetailResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCommunityBoardDetailResponse(
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
)

fun GetCommunityBoardDetailResponse.toModel() = GetCommunityBoardDetailResponseModel(
    title = title,
    content = content
)