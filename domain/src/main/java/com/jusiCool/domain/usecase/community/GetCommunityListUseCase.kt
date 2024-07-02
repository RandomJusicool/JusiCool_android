package com.jusiCool.domain.usecase.community

import com.jusiCool.domain.repository.CommunityRepository
import javax.inject.Inject


class GetCommunityListUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke() = runCatching {
        repository.getCommunityList()
    }
}