package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.like.RemoteLikeDataSource
import com.jusiCool.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val dataSource : RemoteLikeDataSource
) : LikeRepository {
    override suspend fun postLike(boardId: Long): Flow<Unit> {
        return dataSource.postLike(boardId)
    }

    override suspend fun deleteLike(boardId: Long): Flow<Unit> {
        return dataSource.deleteLike(boardId)
    }

    override suspend fun getLike(boardId: Long): Flow<Boolean> {
        return dataSource.getLike(boardId)
    }
}