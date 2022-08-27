package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.ImageApi
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class MainViewModel : ViewModel() {
    private val _response = MutableLiveData<List<Asteroid>>()
    val response : LiveData<List<Asteroid>>
    get() = _response
    private val _navigateToDetails = MutableLiveData<Asteroid>()
    val navigateToDetails : LiveData<Asteroid>
    get() = _navigateToDetails
    private val _picOfToday = MutableLiveData<PictureOfDay>()
    val picOfToday : LiveData<PictureOfDay>
    get() = _picOfToday


    init {
        viewModelScope.launch {
            showAsteroids()
           getTodayPic()
        }


    }

    suspend fun getTodayPic() {
        viewModelScope.launch {
            val networkResponse = ImageApi.retrofitService.getPictureofTheDay("HXmvjmeWkFttStMWa2UZ8boWKSEhVEYkbTttuHHV")
            _picOfToday.value = networkResponse
        }

    }

    private fun getToday(): String {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(currentTime)
    }

  suspend  fun showAsteroids(){
//call getasteroids method to make network request ans add enqueue method to run on background thread
        //enqueue takes callback class as parameter which has methods to be called in success or failure
      viewModelScope.launch {
          val networkResponse =  NasaApi.retrofitservice.getAsteroids(getToday(), key = "HXmvjmeWkFttStMWa2UZ8boWKSEhVEYkbTttuHHV")
          _response.value = parseAsteroidsJsonResult(JSONObject(networkResponse))

      }

    }
    fun onAsteroidClicked(asteroid: Asteroid){
        _navigateToDetails.value = asteroid
    }
    fun doneClick(){
        _navigateToDetails.value = null
    }

}