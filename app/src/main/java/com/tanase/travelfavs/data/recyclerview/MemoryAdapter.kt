package com.tanase.travelfavs.data.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanase.travelfavs.databinding.ItemMemoryBinding
import com.tanase.travelfavs.domain.Memory

class MemoryAdapter(private var memorylist: List<Memory>) :
    RecyclerView.Adapter<MemoryAdapter.MemoryHolder>() {
    var onDeleteMemoryButtonClick: ((Memory) -> Unit)? = null
    var onEditMemoryButtonClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryHolder {
        val binding = ItemMemoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoryHolder(binding)
    }


    override fun onBindViewHolder(holder: MemoryHolder, position: Int) {
        holder.bind(memorylist[position])
    }

    override fun getItemCount(): Int {
        return memorylist.size
    }

    fun addAllMemories(memories: List<Memory>) {
        memorylist = emptyList()
        memorylist = memories
        notifyDataSetChanged()
    }

    inner class MemoryHolder(private val binding: ItemMemoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memory: Memory) {
            binding.destination.text = memory.placeName
            binding.date.text = memory.date
        }

        init {
            binding.ibDeleteMemory.setOnClickListener {
                onDeleteMemoryButtonClick?.invoke(memorylist[adapterPosition])
            }

            binding.ibEditMemory.setOnClickListener {
                onEditMemoryButtonClick?.invoke(memorylist[adapterPosition].uid)

            }
        }
    }
}