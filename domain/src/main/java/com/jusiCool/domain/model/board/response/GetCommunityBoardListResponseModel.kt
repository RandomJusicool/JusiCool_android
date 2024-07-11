package com.jusiCool.domain.model.board.response

data class GetCommunityBoardListResponseModel(
    val id: Long,
    val community_name: String,
    val name: String,
    val title: String,
    val content: String,
    val created_at: String,
    val likes: Int,
    val comment_num: Int,
)
