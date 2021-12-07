package com.nester.openweathermap.Network

import com.nester.openweathermap.Model.City
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {

    suspend fun getCity(city: String, appId: String, units: String): City =
        apiService.getCityData(city, appId, units)
}