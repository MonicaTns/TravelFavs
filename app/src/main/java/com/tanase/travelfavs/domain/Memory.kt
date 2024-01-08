package com.tanase.travelfavs.domain

data class Memory(
    val uid: Int = 0,
    val placeName: String?,
    val date: String?,
    val placeLocation: String?,
    val travelType: String?,
    val travelMood: String?,
    val travelNotes: String?,
    val favourite: Boolean
)
