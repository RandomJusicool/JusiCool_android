package com.jusiCool.data.remote.dto.board.request

import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WritingCommunityBoardRequest(
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
)

fun WritingCommunityBoardRequestModel.toDto() = WritingCommunityBoardRequest(
    title = title,
    content = content
)