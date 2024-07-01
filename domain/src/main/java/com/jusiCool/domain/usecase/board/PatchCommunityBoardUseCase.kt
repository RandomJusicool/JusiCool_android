package com.jusiCool.domain.usecase.board

import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.repository.BoardRepository
import javax.inject.Inject

class PatchCommunityBoardUseCase @Inject constructor(
    private val repository: BoardRepository
) {
    suspend operator fun invoke(
        boardId: Long,
        body: WritingCommunityBoardRequestModel
    ) = runCatching {
        repository.patchCommunityBoard(
            boardId = boardId,
            body = body
        )
    }
}