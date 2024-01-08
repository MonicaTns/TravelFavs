package com.tanase.travelfavs.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoryD::class], version = 1, exportSchema = false)
abstract class TravelFavsDatabase : RoomDatabase() {

    abstract fun memoryDao(): MemoryDao

    companion object {
        @Volatile
        private var INSTANCE: TravelFavsDatabase? = null

        fun getDatabase(context: Context): TravelFavsDatabase {
            Log.e("AddMemoryFragmentViewModel", "getDatabase")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TravelFavsDatabase::class.java,
                    "TravelFavsDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}