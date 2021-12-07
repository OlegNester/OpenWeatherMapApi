package com.nester.openweathermap.Model

data class City(
    val weather: ArrayList<Weather>,
    val main: Main,
    val wind: Wind,
    val name: String,
    val visibility: Int
)

