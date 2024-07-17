package com.jusiCool.domain.usecase.like

import com.jusiCool.domain.repository.LikeRepository
import javax.inject.Inject

class PostLikeUseCase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    suspend operator fun invoke(boardId: String) = runCatching {
        likeRepository.postLike(boardId = boardId)
    }
}