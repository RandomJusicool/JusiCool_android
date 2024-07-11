package com.jusiCool.domain.usecase.day

import com.jusiCool.domain.repository.DayRepository
import javax.inject.Inject

class GetDayUseCase @Inject constructor(
    private val dayRepository: DayRepository,
) {
    suspend operator fun invoke(stockCode: String) = runCatching {
        dayRepository.getGraphData(stockCode)
    }
}