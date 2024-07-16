package com.jusiCool.domain.repository

import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.model.board.response.*
import kotlinx.coroutines.flow.Flow

interface BoardRepository {
    suspend fun getCommunityBoardList(communityId: String) : Flow<List<GetCommunityBoardListResponseModel>>
    suspend fun getCommunityDetail(boardId: String) : Flow<GetCommunityBoardDetailResponseModel>
    suspend fun postCommunityBoard(communityId: String ,body: WritingCommunityBoardRequestModel) : Flow<Unit>
    suspend fun patchCommunityBoard(boardId: String, body: WritingCommunityBoardRequestModel) : Flow<Unit>
    suspend fun deleteCommunityBoard(communityId: String,boardId: String) : Flow<Unit>
}