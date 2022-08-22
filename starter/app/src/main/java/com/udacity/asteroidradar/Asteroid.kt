package com.udacity.asteroidradar

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
//@Entity(tableName = "asteroid_table")
data class Asteroid(
   // @PrimaryKey
    val id: Long = 0L,
   // @ColumnInfo(name="asteroid_codename")
    val codename: String,
   // @ColumnInfo(name="asteroid_approach_date")
    val closeApproachDate: String,
    // @ColumnInfo(name="asteroid_magnitude")
    val absoluteMagnitude: Double= 0.0,
    //@ColumnInfo(name="asteroid_diameter")
    val estimatedDiameter: Double = 0.0,
    //@ColumnInfo(name="asteroid_relative_velocity")
    val relativeVelocity: Double= 0.0,
    // @ColumnInfo(name="asteroid_codename")
    val distanceFromEarth: Double= 0.0,
    // @ColumnInfo(name="asteroid_is_hazardous")
    val isPotentiallyHazardous: Boolean) : Parcelable