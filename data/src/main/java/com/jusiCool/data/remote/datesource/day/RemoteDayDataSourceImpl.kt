package com.jusiCool.data.remote.datesource.day

import com.jusiCool.data.remote.api.DayAPI
import com.jusiCool.data.remote.dto.day.GetDayResponse
import com.jusiCool.data.utill.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RemoteDayDataSourceImpl @Inject constructor(
    private val dayAPI: DayAPI,
) : RemoteDayDataSource {
    override suspend fun getGraphData(stockCode: String): Flow<List<GetDayResponse>> =
        performApiRequest { dayAPI.getGraphData(stockCode) }
}