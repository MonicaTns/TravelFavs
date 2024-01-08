package com.tanase.travelfavs.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemoryD(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "place_name") val placeName: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "place_location") val placeLocation: String?,
    @ColumnInfo(name = "travel_type") val travelType: String?,
    @ColumnInfo(name = "travel_mood") val travelMood: String?,
    @ColumnInfo(name = "travel_notes") val travelNotes: String?,
    @ColumnInfo(name = "favourite") val favourite: Boolean
)
