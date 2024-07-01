package com.jusiCool.data.remote.dto.comment.reqeust

import com.jusiCool.domain.model.comment.request.PostWritingCommunityCommentRequestModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostWritingCommunityCommentRequest(
    @Json(name = "content") val content: String,
)

fun PostWritingCommunityCommentRequestModel.toDto() = PostWritingCommunityCommentRequest(content = content)