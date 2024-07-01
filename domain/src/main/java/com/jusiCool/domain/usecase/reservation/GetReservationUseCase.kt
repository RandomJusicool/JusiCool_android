package com.jusiCool.domain.usecase.reservation

import com.jusiCool.domain.repository.ReservationRepository
import javax.inject.Inject

class GetReservationUseCase @Inject constructor(
    private val repository: ReservationRepository
) {
    suspend operator fun invoke() = runCatching { repository.getReservationStock() }
}