package com.udacity.asteroidradar

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

//dependecy injunction of database to avoid using context
class AsteroidRepository(private val database: AsteroidDatabase) {
    //local variable retreived to update screen
    val asteroids : LiveData<List<Asteroid>> = Transformations.map(database.asteroidDatabaseDao.getAllAsteroids()){
        it.asDomainModel()
    }


 //function to update asteroid database
    suspend fun refreshAsteroids(){
        //Writing to daatabase is an input operation so IO Dispatcher
        withContext(Dispatchers.IO){
            val asteroids = parseAsteroidsJsonResult(JSONObject(
                NasaApi.retrofitservice.getAsteroids(getToday(),key="HXmvjmeWkFttStMWa2UZ8boWKSEhVEYkbTttuHHV")
            ))
            database.asteroidDatabaseDao.addAsteroids(*asteroids.asDatabaseModel())

        }
    }
    private fun getToday(): String {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(currentTime)
    }
}