package com.jusiCool.data.remote.dto.board.response

import com.jusiCool.domain.model.board.response.GetCommunityBoardDetailResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCommunityBoardDetailResponse(
    @Json(name = "community_name") val communityName: String,
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
    @Json(name = "likes") val likes: Int,
)

fun GetCommunityBoardDetailResponse.toModel() = GetCommunityBoardDetailResponseModel(
    communityName = communityName,
    title = title,
    content = content,
    likes = likes
)