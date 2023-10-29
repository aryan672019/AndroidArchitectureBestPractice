package com.letmelearn.architecturepractice.di

import com.letmelearn.architecturepractice.data.dataSources.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    const val BASE_URL = "https://api.weatherapi.com"

    @Provides
    fun provideRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Provides
    fun provideWeatherApi(retrofit: Retrofit):WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }
}