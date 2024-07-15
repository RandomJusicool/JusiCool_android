package com.jusiCool.domain.model.board.response

data class GetCommunityBoardDetailResponseModel(
    val communityName: String,
    val title: String,
    val content: String,
    val likes: Int
)
