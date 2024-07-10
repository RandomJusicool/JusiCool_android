package com.jusiCool.domain.repository

import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    suspend fun postLike(boardId: Long) : Flow<Unit>
    suspend fun deleteLike(boardId: Long) : Flow<Unit>
}