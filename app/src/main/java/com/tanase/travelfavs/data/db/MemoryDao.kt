package com.tanase.travelfavs.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {
    @Query("SELECT * FROM MemoryD")
    fun getAll(): Flow<List<MemoryD>>

    @Delete
    suspend fun delete(memoryD: MemoryD)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrEdit(memoryD: MemoryD)
}