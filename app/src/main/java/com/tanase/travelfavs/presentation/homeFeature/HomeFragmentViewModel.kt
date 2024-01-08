package com.tanase.travelfavs.presentation.homeFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tanase.travelfavs.data.recyclerview.MemoryAdapter
import com.tanase.travelfavs.domain.Memory
import com.tanase.travelfavs.domain.repository.MemoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val repository: MemoriesRepository) : ViewModel() {

    private val _memories = MutableLiveData<List<Memory>>()
    val memories: LiveData<List<Memory>> = _memories
    private val _memoryListFlow = repository.getAll()
    val memoryListFlow: Flow<List<Memory>> get() = _memoryListFlow

    companion object {
        fun provideFactory(myRepository: MemoriesRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeFragmentViewModel(myRepository) as T
                }
            }
    }
    suspend fun deleteMemory(memory: Memory) {
        repository.delete(memory)
    }

    suspend fun editMemory(memory:Memory){
        repository.insertOrEdit(memory)
    }
}