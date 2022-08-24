package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants.BASE_URL
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface NasaApiService {
    @GET("neo/rest/v1/feed?start_date=2022-08-30&end_date=2022-09-06&api_key=HXmvjmeWkFttStMWa2UZ8boWKSEhVEYkbTttuHHV")
    fun getAsteroids(): Call<String>

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