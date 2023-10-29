package com.letmelearn.architecturepractice.data.repo

import com.letmelearn.architecturepractice.data.apiModels.SearchLocationApiModel
import com.letmelearn.architecturepractice.data.dataSources.SearchLocationNetworkDataSource
import com.letmelearn.architecturepractice.data.dataSources.api.WeatherApi
import com.letmelearn.architecturepractice.data.externalModels.SearchLocationExternalModel
import com.letmelearn.architecturepractice.data.externalModels.externalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchLocationRepository @Inject constructor(
    private val searchLocationNetworkDataSource: SearchLocationNetworkDataSource
) {

    /**
     * This is an app oriented operation, so even if user moves away from screen, this should be running and
     * cached it also
     */

    suspend fun searchLocation(locationName: String): Flow<SearchLocationExternalModel>  =
        searchLocationNetworkDataSource.getLocationWeather(locationName)
            .map { it.externalModel() }
            .flowOn(Dispatchers.Default)
            .catch { emit(SearchLocationApiModel().externalModel()) }


}