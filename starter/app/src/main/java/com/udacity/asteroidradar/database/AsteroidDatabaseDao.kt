package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.DatabaseAsteroid

@Dao
interface AsteroidDatabaseDao {
    //when getting new asteroids from api, conflict strategy is to replace asteroids with same id
@Insert(onConflict = OnConflictStrategy.REPLACE)
fun addAsteroids(vararg  asteroids: DatabaseAsteroid)
@Query("SELECT * FROM asteroid_table ORDER BY asteroid_approach_date ASC")
 fun getAllAsteroids():LiveData<List<DatabaseAsteroid>>
 @Query("SELECT * FROM asteroid_table WHERE asteroid_approach_date = :today")
 fun getTodayAsteroids(today: String):LiveData<List<DatabaseAsteroid>>
    @Query("SELECT * FROM asteroid_table WHERE asteroid_approach_date >= :today ORDER BY asteroid_approach_date ASC")
    fun getWeekAsteroids(today: String):LiveData<List<DatabaseAsteroid>>
    @Query("DELETE FROM asteroid_table WHERE asteroid_approach_date < date(:today)")
    fun deleteOldAsteroids(today: String)

}