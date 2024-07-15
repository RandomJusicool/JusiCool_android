package com.jusiCool.domain.usecase.user

import com.jusiCool.domain.repository.UserRepository
import javax.inject.Inject

class GetMyStockUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = runCatching {
        repository.getMyStock()
    }
}