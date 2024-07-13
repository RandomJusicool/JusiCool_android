package com.jusiCool.domain.repository

import com.jusiCool.domain.model.day.GetDayModel
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    suspend fun getGraphData(stockCode: String) : Flow<List<GetDayModel>>
}