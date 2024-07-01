package com.jusiCool.domain.model.board.response

data class GetCommunityBoardListResponseModel(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val likes: Int,
    val commentNum: Int,
)
