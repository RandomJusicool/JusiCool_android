package com.jusiCool.domain.usecase.like

import com.jusiCool.domain.repository.LikeRepository
import javax.inject.Inject

class GetLikeUseCase @Inject constructor(
    private val repository: LikeRepository
) {
    suspend operator fun invoke(boardId: String) = runCatching {
        repository.getLike(boardId = boardId)
    }
}