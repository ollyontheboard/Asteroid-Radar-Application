package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Asteroid

@Database(entities = [AsteroidDatabase::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {
abstract val asteroidDatabaseDao: AsteroidDatabaseDao
    companion object{
        @Volatile
       private var INSTANCE : AsteroidDatabase? = null

        fun getInstance(context: Context): AsteroidDatabase{
            synchronized(AsteroidDatabase::class.java){
            var instance = INSTANCE
            if(instance==null){
                instance = Room.databaseBuilder(context.applicationContext,
                    AsteroidDatabase::class.java,"asteroid_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }

            return instance
        }
        }
    }
}