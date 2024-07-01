package com.jusiCool.domain.usecase.board

import com.jusiCool.domain.repository.BoardRepository
import javax.inject.Inject

class GetCommunityDetailUseCase @Inject constructor(
    private val repository: BoardRepository
) {
    suspend operator fun invoke(boardId: Long) = runCatching {
        repository.getCommunityDetail(boardId = boardId)
    }
}