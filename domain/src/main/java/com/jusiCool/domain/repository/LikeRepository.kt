package com.jusiCool.domain.repository

import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    suspend fun postLike(boardId: String) : Flow<Unit>
    suspend fun deleteLike(boardId: String) : Flow<Unit>
    suspend fun getLike(boardId: String) : Flow<Boolean>
}