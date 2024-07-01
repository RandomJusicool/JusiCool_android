package com.jusiCool.domain.usecase.board

import com.jusiCool.domain.repository.BoardRepository
import javax.inject.Inject

class GetCommunityListUseCase @Inject constructor(
    private val repository: BoardRepository
){
    suspend operator fun invoke(communityId: Long) = runCatching {
        repository.getCommunityList(communityId = communityId)
    }
}