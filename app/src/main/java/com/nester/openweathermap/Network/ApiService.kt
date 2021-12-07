package com.nester.openweathermap.Network

import com.nester.openweathermap.Model.City
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather/")
    suspend fun getCityData(
        @Query("q") q: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): City
}