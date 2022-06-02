package com.agostinho.witchallenge.repository

import com.agostinho.witchallenge.models.WeatherResult
import com.agostinho.witchallenge.network.WeatherApi
import com.agostinho.witchallenge.utils.DataState
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository
constructor(
    private val weatherApi: WeatherApi
)
{
    suspend fun getCitiesWeather(cityIds:String, apiKey:String, units: String):Flow<DataState<List<WeatherResult>>> = flow {
        emit(DataState.Loading)
        try {
            val networkWeather = weatherApi.getWeatherByCityId(cityIds, apiKey, units)
            emit(DataState.Success(networkWeather.list))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}