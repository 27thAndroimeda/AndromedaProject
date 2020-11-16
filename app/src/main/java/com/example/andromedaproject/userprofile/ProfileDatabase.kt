package com.example.andromedaproject.userprofile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomProfile::class),version =  1, exportSchema = false)
abstract class ProfileRoomHelper: RoomDatabase() {
    abstract fun roomProfileDao(): RoomProfileDao
}