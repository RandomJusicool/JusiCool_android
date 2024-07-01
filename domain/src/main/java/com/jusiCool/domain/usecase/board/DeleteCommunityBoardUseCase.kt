package com.jusiCool.domain.usecase.board

import com.jusiCool.domain.repository.BoardRepository
import javax.inject.Inject

class DeleteCommunityBoardUseCase @Inject constructor(
    private val repository: BoardRepository
){
    suspend operator fun invoke(boardId: Long) = runCatching { repository.deleteCommunityBoard(boardId = boardId) }
}