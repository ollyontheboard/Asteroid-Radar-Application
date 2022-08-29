package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.AsteroidRepository
import com.udacity.asteroidradar.database.AsteroidDatabase.Companion.getInstance
import java.lang.Exception

class AsteroidWorker(appContext: Context, params: WorkerParameters):
//Background work to download asteroids
    CoroutineWorker(appContext,params) {

    companion object{
        const val WORK_NAME = "RefreshAsteroidWorker"
    }
    override suspend fun doWork(): Result {
        val database = getInstance(applicationContext)
        val repository = AsteroidRepository(database)

        return try {
            repository.refreshAsteroids()
            Result.success()
        }
        catch (e: Exception){
            Result.retry()
        }


    }
}