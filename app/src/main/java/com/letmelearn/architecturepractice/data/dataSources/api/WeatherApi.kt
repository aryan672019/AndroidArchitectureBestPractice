package com.letmelearn.architecturepractice.data.dataSources.api

import com.letmelearn.architecturepractice.data.apiModels.SearchLocationApiModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getLocationWeather(
        @Query("q") locationName: String,
        @Query("key") apiKey: String,
        @Query("aqi") aqi: String
    ):SearchLocationApiModel
}