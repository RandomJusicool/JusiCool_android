package com.jusiCool.data.remote.datesource.like

import com.jusiCool.data.remote.api.LikeAPI
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteLikeDataSourceImpl @Inject constructor(
    private val serviceLike: LikeAPI
) : RemoteLikeDataSource {
    override suspend fun postLike(boardId: Long): Flow<Unit> =
        performApiRequest { serviceLike.postLike(boardId = boardId) }

    override suspend fun deleteLike(boardId: Long): Flow<Unit> =
        performApiRequest { serviceLike.deleteLike(boardId = boardId) }

    override suspend fun getLike(boardId: Long): Flow<Boolean> =
        performApiRequest { serviceLike.getLike(boardId = boardId) }
}