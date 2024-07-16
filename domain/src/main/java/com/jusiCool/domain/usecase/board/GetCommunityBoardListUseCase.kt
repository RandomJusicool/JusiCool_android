package com.jusiCool.domain.usecase.board

import com.jusiCool.domain.repository.BoardRepository
import javax.inject.Inject

class GetCommunityBoardListUseCase @Inject constructor(
    private val repository: BoardRepository
){
    suspend operator fun invoke(communityId: String) = runCatching {
        repository.getCommunityBoardList(communityId = communityId)
    }
}