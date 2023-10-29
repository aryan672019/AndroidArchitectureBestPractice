package com.letmelearn.architecturepractice.data.dataSources

import android.util.Log
import com.letmelearn.architecturepractice.data.apiModels.Current
import com.letmelearn.architecturepractice.data.apiModels.Location
import com.letmelearn.architecturepractice.data.apiModels.SearchLocationApiModel
import com.letmelearn.architecturepractice.data.dataSources.api.WeatherApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchLocationNetworkDataSource @Inject constructor(private val weatherApi: WeatherApi,) {

    suspend fun getLocationWeather(location: String): Flow<SearchLocationApiModel> = flow {
       emit(weatherApi.getLocationWeather(locationName = location, aqi = "no", apiKey = "ce7f221d3b6e45f2b8b92641232810"))
    }
}
