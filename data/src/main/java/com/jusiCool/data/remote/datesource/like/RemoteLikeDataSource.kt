package com.jusiCool.data.remote.datesource.like

import kotlinx.coroutines.flow.Flow

interface RemoteLikeDataSource{
    suspend fun postLike(boardId: String) : Flow<Unit>
    suspend fun deleteLike(boardId: String) : Flow<Unit>
    suspend fun getLike(boardId: String) : Flow<Boolean>
}