package com.jusiCool.data.remote.dto.board.response

import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCommunityBoardListResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "community_name") val community_name: String,
    @Json(name = "name") val name: String,
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
    @Json(name = "created_at") val created_at: String,
    @Json(name = "likes") val likes: Int,
    @Json(name = "comment_num") val comment_num: Int,
)

fun GetCommunityBoardListResponse.toModel() = GetCommunityBoardListResponseModel(
    id = id.toString(),
    community_name = community_name,
    name = name,
    title = title,
    content = content,
    created_at = created_at,
    likes = likes,
    comment_num  = comment_num
)