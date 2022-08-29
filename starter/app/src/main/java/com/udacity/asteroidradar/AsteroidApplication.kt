package com.udacity.asteroidradar

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.work.AsteroidWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidApplication: Application() {
    val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        refreshAsteroids()
    }
    private fun refreshAsteroids(){
        applicationScope.launch {
            setupWork()
        }

    }

    private fun setupWork() {
        //setup constraint of phone being connected to wifi, and battery charging
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                //if phone uses android marshmallow use device idle
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                    setRequiresDeviceIdle(true)
                }
            }.build()
        //periodic work request not one time because refreshing should happen more than once
        val refreshRequest = PeriodicWorkRequestBuilder<AsteroidWorker>(
            1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()
        //enqueue periodic work
        //pass work name constant, policy to keep instead of replace if there is already a worker with given workname
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            AsteroidWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            refreshRequest
        )
    }
}