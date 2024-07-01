package com.jusiCool.data.remote.datesource.board

import com.jusiCool.data.remote.dto.board.request.WritingCommunityBoardRequest
import com.jusiCool.data.remote.dto.board.response.*
import kotlinx.coroutines.flow.Flow

interface RemoteBoardDataSource {
    suspend fun getCommunityList(communityId: Long) : Flow<List<GetCommunityBoardListResponse>>
    suspend fun getCommunityDetail(boardId: Long) : Flow<GetCommunityBoardDetailResponse>
    suspend fun postCommunityBoard(communityId: Long ,body: WritingCommunityBoardRequest) : Flow<Unit>
    suspend fun patchCommunityBoard(boardId: Long, body: WritingCommunityBoardRequest) : Flow<Unit>
    suspend fun deleteCommunityBoard(boardId: Long) : Flow<Unit>
}