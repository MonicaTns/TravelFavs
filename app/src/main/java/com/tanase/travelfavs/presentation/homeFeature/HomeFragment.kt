package com.tanase.travelfavs.presentation.homeFeature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tanase.travelfavs.R
import com.tanase.travelfavs.TravelFavsApplication
import com.tanase.travelfavs.data.recyclerview.MemoryAdapter
import com.tanase.travelfavs.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MemoryAdapter

    private val viewModel: HomeFragmentViewModel by viewModels {
        HomeFragmentViewModel.provideFactory((activity?.application as TravelFavsApplication).memoriesRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fabHomeToAddMemorySetup()
        collectMemoryFlow()
        setupRecyclerView()
    }

    private fun fabHomeToAddMemorySetup() {
        binding.fabAddMemory.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addMemoryFragment)
        }
    }

    private fun collectMemoryFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.memoryListFlow.collect { memories ->
                Log.e("77", "${memories.size}")
                adapter.addAllMemories(memories)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MemoryAdapter(emptyList())
        binding.rvHome.adapter = adapter
        adapter.onDeleteMemoryButtonClick = { memory ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.deleteMemory(memory)
            }
        }

        adapter.onEditMemoryButtonClick = { memory ->


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}