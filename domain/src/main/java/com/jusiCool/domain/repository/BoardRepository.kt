package com.jusiCool.domain.repository

import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.model.board.response.*
import kotlinx.coroutines.flow.Flow

interface BoardRepository {
    suspend fun getCommunityList(communityId: Long) : Flow<List<GetCommunityBoardListResponseModel>>
    suspend fun getCommunityDetail(boardId: Long) : Flow<GetCommunityBoardDetailResponseModel>
    suspend fun postCommunityBoard(communityId: Long ,body: WritingCommunityBoardRequestModel) : Flow<Unit>
    suspend fun patchCommunityBoard(boardId: Long, body: WritingCommunityBoardRequestModel) : Flow<Unit>
    suspend fun deleteCommunityBoard(boardId: Long) : Flow<Unit>
}