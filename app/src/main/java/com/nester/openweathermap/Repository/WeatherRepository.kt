package com.nester.openweathermap.Repository

import com.nester.openweathermap.Model.City
import com.nester.openweathermap.Network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityData(city: String): Flow<City> = flow {
        val response = apiServiceImp.getCity(city, "1ea6a44945b7af4f34ead62aa5f88981", "metric")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}