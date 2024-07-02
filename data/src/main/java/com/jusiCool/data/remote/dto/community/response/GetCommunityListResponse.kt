package com.jusiCool.data.remote.dto.community.response

import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCommunityListResponse(
    @Json(name = "name") val name: String,
    @Json(name = "board_num") val board_num: Int,
)

fun GetCommunityListResponse.toModel() = GetCommunityListResponseModel(
    name = name,
    board_num = board_num
)