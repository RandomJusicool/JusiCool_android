package com.jusiCool.data.remote.datesource.board

import com.jusiCool.data.remote.api.BoardAPI
import com.jusiCool.data.remote.dto.board.request.WritingCommunityBoardRequest
import com.jusiCool.data.remote.dto.board.response.GetCommunityBoardDetailResponse
import com.jusiCool.data.remote.dto.board.response.GetCommunityBoardListResponse
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteBoardDataSourceImpl @Inject constructor(
    private val boardService: BoardAPI
) : RemoteBoardDataSource {
    override suspend fun getCommunityList(communityId: Long): Flow<List<GetCommunityBoardListResponse>> =
        performApiRequest { boardService.getCommunityList(communityId = communityId) }

    override suspend fun getCommunityDetail(boardId: Long): Flow<GetCommunityBoardDetailResponse> =
        performApiRequest { boardService.getCommunityDetail(boardId = boardId) }

    override suspend fun postCommunityBoard(
        communityId: Long,
        body: WritingCommunityBoardRequest
    ): Flow<Unit> =
        performApiRequest { boardService.postWritingCommunity(
            communityId = communityId,
            body = body
        ) }

    override suspend fun patchCommunityBoard(
        boardId: Long,
        body: WritingCommunityBoardRequest
    ): Flow<Unit> =
        performApiRequest { boardService.patchBoardCommunity(
            boardId = boardId,
            body = body
        ) }

    override suspend fun deleteCommunityBoard(boardId: Long): Flow<Unit> =
        performApiRequest { boardService.deleteBoardCommunity(boardId = boardId) }
}