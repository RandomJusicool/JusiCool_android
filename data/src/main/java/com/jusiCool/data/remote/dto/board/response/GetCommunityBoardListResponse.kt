package com.jusiCool.data.remote.dto.board.response

import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCommunityBoardListResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "likes") val likes: Int,
    @Json(name = "commentNum") val commentNum: Int,
)

fun GetCommunityBoardListResponse.toModel() = GetCommunityBoardListResponseModel(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    likes = likes,
    commentNum = commentNum
)