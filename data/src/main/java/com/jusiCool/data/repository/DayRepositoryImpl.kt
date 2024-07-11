package com.jusiCool.data.repository

import com.jusiCool.data.remote.datesource.day.RemoteDayDataSource
import com.jusiCool.data.remote.dto.day.toModel
import com.jusiCool.domain.model.day.GetDayModel
import com.jusiCool.domain.repository.DayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DayRepositoryImpl @Inject constructor(
    private val remoteDayDataSource: RemoteDayDataSource,
) : DayRepository {
    override suspend fun getGraphData(stockCode: String): Flow<List<GetDayModel>> {
        return remoteDayDataSource.getGraphData(stockCode).map { list -> list.map { it.toModel() } }
    }
}