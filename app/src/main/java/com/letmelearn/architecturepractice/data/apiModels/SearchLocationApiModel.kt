package com.letmelearn.architecturepractice.data.apiModels


import com.google.gson.annotations.SerializedName

data class SearchLocationApiModel(
    @SerializedName("current")
    val current: Current = Current(),
    @SerializedName("location")
    val location: Location = Location()
)


