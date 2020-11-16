package com.example.andromedaproject.userprofile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_data")
class RoomProfile {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var number: Long? = null

    @ColumnInfo
    var imageProfile: String? = null

    @ColumnInfo
    var nameProfile: String? = null

    @ColumnInfo
    var descriptionProfile: String? = null

    constructor(imageProfile: String, nameProfile: String, descriptionProfile: String) {
        this.imageProfile = imageProfile
        this.nameProfile = nameProfile
        this.descriptionProfile = descriptionProfile
    }
}