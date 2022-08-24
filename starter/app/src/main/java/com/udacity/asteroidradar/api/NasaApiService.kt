package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants.BASE_URL
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("neo/rest/v1/feed")
 suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("api_key") key: String
   ): String

}
private val retrofit = Retrofit.Builder()
   .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


object NasaApi{
    val retrofitservice : NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}