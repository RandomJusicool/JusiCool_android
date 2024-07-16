package com.jusiCool.domain.usecase.receipt

import com.jusiCool.domain.repository.ReceiptRepository
import javax.inject.Inject

class GetReceiptUseCase @Inject constructor(
    private val repository: ReceiptRepository
) {
    suspend operator fun invoke() = runCatching {
        repository.getReceipt()
    }
}