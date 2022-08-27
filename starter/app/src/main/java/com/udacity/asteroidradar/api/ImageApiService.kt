package com.udacity.asteroidradar.api

import android.media.Image
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    @GET("/planetary/apod")
    suspend fun getPictureofTheDay(
        @Query("api_key")key: String
    ): PictureOfDay

}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

object ImageApi{
    val retrofitService: ImageApiService by lazy{
        retrofit.create(ImageApiService::class.java)
    }
}