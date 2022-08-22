package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.api.NasaApi
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
    get() = _response

    init {
        showAsteroids()
    }

    fun showAsteroids(){
//call getasteroids method to make network request ans add enqueue method to run on background thread
        //enqueue takes callback class as parameter which has methods to be called in success or failure
        NasaApi.retrofitservice.getAsteroids().enqueue(object: retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure"+ t.message
            }
        })
    }
}