package com.jusiCool.data.remote.dto.comment.response

import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCommunityCommentResponse(
    @Json(name = "name") val name: String,
    @Json(name = "content") val content: String,
)

fun GetCommunityCommentResponse.toModel() = GetCommunityCommentResponseModel(
    name = name,
    content = content,
)