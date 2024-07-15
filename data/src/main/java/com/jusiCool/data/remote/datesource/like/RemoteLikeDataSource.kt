package com.jusiCool.data.remote.datesource.like

import kotlinx.coroutines.flow.Flow

interface RemoteLikeDataSource{
    suspend fun postLike(boardId: Long) : Flow<Unit>
    suspend fun deleteLike(boardId: Long) : Flow<Unit>
    suspend fun getLike(boardId: Long) : Flow<Boolean>
}