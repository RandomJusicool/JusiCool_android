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
    override suspend fun getCommunityBoardList(communityId: String): Flow<List<GetCommunityBoardListResponseModel>> {
        return dataSource.getCommunityBoardList(communityId = communityId).map { list -> list.map { it.toModel() } }
    }

    override suspend fun getCommunityDetail(boardId: String): Flow<GetCommunityBoardDetailResponseModel> {
        return dataSource.getCommunityDetail(boardId = boardId).map { it.toModel() }
    }

    override suspend fun postCommunityBoard(
        communityId: String,
        body: WritingCommunityBoardRequestModel
    ): Flow<Unit> {
        return dataSource.postCommunityBoard(
            communityId = communityId,
            body = body.toDto()
        )
    }

    override suspend fun patchCommunityBoard(
        boardId: String,
        body: WritingCommunityBoardRequestModel
    ): Flow<Unit> {
        return dataSource.patchCommunityBoard(
            boardId = boardId,
            body = body.toDto()
        )
    }

    override suspend fun deleteCommunityBoard(communityId:String, boardId: String): Flow<Unit> {
        return dataSource.deleteCommunityBoard(
            communityId = communityId,
            boardId = boardId
        )
    }
}