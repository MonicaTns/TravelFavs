package com.tanase.travelfavs.presentation.homeFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tanase.travelfavs.domain.Memory
import com.tanase.travelfavs.domain.repository.MemoriesRepository
import kotlinx.coroutines.launch

class EditMemoryFragmentViewModel(private val repository: MemoriesRepository) : ViewModel() {

    fun createMemoryObject(memory: Memory) {
        viewModelScope.launch {
            repository.insertOrEdit(memory)
        }
    }

    companion object {
        fun provideFactory(myRepository: MemoriesRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return EditMemoryFragmentViewModel(myRepository) as T
                }
            }
    }


}