package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.AsteroidRepository
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.ImageApi
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _response = MutableLiveData<List<Asteroid>>()
    var response : LiveData<List<Asteroid>>
    get() = _response
    private val _navigateToDetails = MutableLiveData<Asteroid>()
    val navigateToDetails : LiveData<Asteroid>
    get() = _navigateToDetails
    private val _picOfToday = MutableLiveData<PictureOfDay>()
    val picOfToday : LiveData<PictureOfDay>
    get() = _picOfToday

    private val database = AsteroidDatabase.getInstance(application)
    private val asteroidRepo = AsteroidRepository(database)



    init {
        viewModelScope.launch {
           getTodayPic()
            asteroidRepo.refreshAsteroids()
        }

        response = asteroidRepo.asteroids


    }

    suspend fun getTodayPic() {
        viewModelScope.launch {
            val networkResponse = ImageApi.retrofitService.getPictureofTheDay("HXmvjmeWkFttStMWa2UZ8boWKSEhVEYkbTttuHHV")
            _picOfToday.value = networkResponse
        }

    }




    fun onAsteroidClicked(asteroid: Asteroid){
        _navigateToDetails.value = asteroid
    }
    fun doneClick(){
        _navigateToDetails.value = null
    }

}