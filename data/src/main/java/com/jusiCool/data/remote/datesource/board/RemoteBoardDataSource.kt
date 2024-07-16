package com.jusiCool.data.remote.datesource.board

import com.jusiCool.data.remote.dto.board.request.WritingCommunityBoardRequest
import com.jusiCool.data.remote.dto.board.response.*
import kotlinx.coroutines.flow.Flow

interface RemoteBoardDataSource {
    suspend fun getCommunityBoardList(communityId: String) : Flow<List<GetCommunityBoardListResponse>>
    suspend fun getCommunityDetail(boardId: String) : Flow<GetCommunityBoardDetailResponse>
    suspend fun postCommunityBoard(communityId: String ,body: WritingCommunityBoardRequest) : Flow<Unit>
    suspend fun patchCommunityBoard(boardId: String, body: WritingCommunityBoardRequest) : Flow<Unit>
    suspend fun deleteCommunityBoard(communityId: String, boardId: String) : Flow<Unit>
}