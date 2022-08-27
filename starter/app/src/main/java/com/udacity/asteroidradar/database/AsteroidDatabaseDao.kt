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
@Query("SELECT * FROM asteroid_table")
 fun getAllAsteroids():LiveData<List<DatabaseAsteroid>>

}