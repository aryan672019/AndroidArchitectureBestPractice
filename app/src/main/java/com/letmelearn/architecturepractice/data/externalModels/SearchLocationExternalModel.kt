package com.letmelearn.architecturepractice.data.externalModels

import com.letmelearn.architecturepractice.data.apiModels.SearchLocationApiModel

data class SearchLocationExternalModel(
    val name:String,
    val lat:Double,
    val lon:Double,
    val tempC:Double,
    val tempF:Double
)



fun SearchLocationApiModel.externalModel() = SearchLocationExternalModel(
    name = this.location.name,
    lat =  this.location.lat,
    lon = this.location.lon,
    tempC = this.current.feelslikeC,
    tempF = this.current.feelslikeF
)