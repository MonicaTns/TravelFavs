package com.tanase.travelfavs.domain.repository

import com.tanase.travelfavs.domain.Memory
import kotlinx.coroutines.flow.Flow

interface MemoriesRepository {
    fun getAll(): Flow<List<Memory>>
    suspend fun delete(memory: Memory)
    suspend fun insertOrEdit(memory: Memory)
}