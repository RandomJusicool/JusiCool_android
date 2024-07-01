package com.jusiCool.domain.usecase.board

import com.jusiCool.domain.model.board.request.WritingCommunityBoardRequestModel
import com.jusiCool.domain.repository.BoardRepository
import javax.inject.Inject

class PostWritingCommunityUseCase @Inject constructor(
    private val repository: BoardRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        body: WritingCommunityBoardRequestModel
    ) = runCatching {
        repository.postCommunityBoard(
            communityId = communityId,
            body = body
        )
    }
}