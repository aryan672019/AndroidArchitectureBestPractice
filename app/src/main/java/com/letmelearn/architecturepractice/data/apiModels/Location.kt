package com.letmelearn.architecturepractice.data.apiModels


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    val country: String = "",
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("localtime")
    val localtime: String = "",
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Int = 0,
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("region")
    val region: String = "",
    @SerializedName("tz_id")
    val tzId: String = ""
)