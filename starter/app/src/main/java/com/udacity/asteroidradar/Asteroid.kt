package com.udacity.asteroidradar

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//Database object
@Entity(tableName = "asteroid_table")
data class DatabaseAsteroid(
    @PrimaryKey
    val id: Long ,
    @ColumnInfo(name="asteroid_codename")
    val codename: String,
    @ColumnInfo(name="asteroid_approach_date")
    val closeApproachDate: String,
     @ColumnInfo(name="asteroid_magnitude")
    val absoluteMagnitude: Double,
    @ColumnInfo(name="asteroid_diameter")
    val estimatedDiameter: Double ,
    @ColumnInfo(name="asteroid_relative_velocity")
    val relativeVelocity: Double,
     @ColumnInfo(name="asteroid_distance")
    val distanceFromEarth: Double,
     @ColumnInfo(name="asteroid_is_hazardous")
    val isPotentiallyHazardous: Boolean)


//Domain object, used to display in ui. Should be separate from database object
@Parcelize
data class Asteroid(
val id: Long ,
val codename: String,
val closeApproachDate: String,
val absoluteMagnitude: Double,
val estimatedDiameter: Double ,
val relativeVelocity: Double,
val distanceFromEarth: Double,
val isPotentiallyHazardous: Boolean) : Parcelable

//function to convert database object to domain model
fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid>{
return map{
    Asteroid(
        id = it.id,
        codename = it.codename,
        closeApproachDate = it.closeApproachDate,
        absoluteMagnitude = it.absoluteMagnitude,
        estimatedDiameter = it.estimatedDiameter,
        relativeVelocity = it.relativeVelocity,
        distanceFromEarth = it.distanceFromEarth,
        isPotentiallyHazardous = it.isPotentiallyHazardous
    )
} }

fun List<Asteroid>.asDatabaseModel(): Array<DatabaseAsteroid>{
    return map{
        DatabaseAsteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}
