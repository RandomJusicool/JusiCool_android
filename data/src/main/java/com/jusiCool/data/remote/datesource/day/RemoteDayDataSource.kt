package com.jusiCool.data.remote.datesource.day

import com.jusiCool.data.remote.dto.day.GetDayResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDayDataSource {
    suspend fun getGraphData(stockCode: String) : Flow<List<GetDayResponse>>
}
