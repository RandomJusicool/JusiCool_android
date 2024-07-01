package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.board.RemoteBoardDataSource
import com.jusiCool.data.remote.dto.board.request.toDto
import com.jusiCool.data.remote.dto.board.response.toModel
import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.model.board.response.*
import com.jusiCool.domain.repository.BoardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BoardRepositoryImpl @Inject constructor(
    private val dataSource: RemoteBoardDataSource
) : BoardRepository {
    override suspend fun getCommunityList(communityId: Long): Flow<List<GetCommunityBoardListResponseModel>> {
        return dataSource.getCommunityList(communityId = communityId).map { list -> list.map { it.toModel() } }
    }

    override suspend fun getCommunityDetail(boardId: Long): Flow<GetCommunityBoardDetailResponseModel> {
        return dataSource.getCommunityDetail(boardId = boardId).map { it.toModel() }
    }

    override suspend fun postCommunityBoard(
        communityId: Long,
        body: WritingCommunityBoardRequestModel
    ): Flow<Unit> {
        return dataSource.postCommunityBoard(
            communityId = communityId,
            body = body.toDto()
        )
    }

    override suspend fun patchCommunityBoard(
        boardId: Long,
        body: WritingCommunityBoardRequestModel
    ): Flow<Unit> {
        return dataSource.patchCommunityBoard(
            boardId = boardId,
            body = body.toDto()
        )
    }

    override suspend fun deleteCommunityBoard(boardId: Long): Flow<Unit> {
        return dataSource.deleteCommunityBoard(boardId = boardId)
    }
}