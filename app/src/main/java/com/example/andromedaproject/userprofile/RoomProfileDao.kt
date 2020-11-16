package com.example.andromedaproject.userprofile

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoomProfileDao {
    @Query("select * from profile_data")
    fun getAll(): List<RoomProfile>

    @Insert(onConflict = REPLACE)
    fun insert(profile: RoomProfile)

    @Delete
    fun delete(profile: RoomProfile)
}