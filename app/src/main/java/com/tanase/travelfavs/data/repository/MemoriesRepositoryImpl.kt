package com.tanase.travelfavs.data.repository

import com.tanase.travelfavs.data.db.MemoryD
import com.tanase.travelfavs.data.db.MemoryDao
import com.tanase.travelfavs.domain.Memory
import com.tanase.travelfavs.domain.repository.MemoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MemoriesRepositoryImpl(private val memoryDao: MemoryDao) : MemoriesRepository {

    override fun getAll(): Flow<List<Memory>> = memoryDao.getAll().map {
        it.map {
            it.toMemory()
        }
    }

    override suspend fun delete(memory: Memory) {
        memoryDao.delete(memory.toMemoryD())
    }

    override suspend fun insertOrEdit(memory: Memory) {
        withContext(Dispatchers.IO) {
            memoryDao.insertOrEdit(memory.toMemoryD())
        }
    }

    private fun Memory.toMemoryD(): MemoryD = MemoryD(
        uid = this.uid,
        placeName = this.placeName,
        date = this.date,
        placeLocation = this.placeLocation,
        travelType = this.travelType,
        travelMood = this.travelMood,
        travelNotes = this.travelNotes,
        favourite = this.favourite
    )

    private fun MemoryD.toMemory(): Memory = Memory(
        uid = this.uid,
        placeName = this.placeName,
        date = this.date,
        placeLocation = this.placeLocation,
        travelType = this.travelType,
        travelMood = this.travelMood,
        travelNotes = this.travelNotes,
        favourite = this.favourite
    )
}