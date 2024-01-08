package com.tanase.travelfavs

import android.app.Application
import com.tanase.travelfavs.data.db.TravelFavsDatabase
import com.tanase.travelfavs.data.repository.MemoriesRepositoryImpl
import com.tanase.travelfavs.domain.repository.MemoriesRepository

class TravelFavsApplication : Application() {

    lateinit var memoriesRepository: MemoriesRepository
        private set

    override fun onCreate() {
        super.onCreate()
        memoriesRepository =
            MemoriesRepositoryImpl(TravelFavsDatabase.getDatabase(this).memoryDao())
    }

}