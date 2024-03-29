package com.tanase.travelfavs.presentation.homeFeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tanase.travelfavs.R
import com.tanase.travelfavs.TravelFavsApplication
import com.tanase.travelfavs.convertDatePickerParamsToDate
import com.tanase.travelfavs.databinding.FragmentAddMemoryBinding
import com.tanase.travelfavs.domain.Memory


class AddMemoryFragment : Fragment() {

    private var _binding: FragmentAddMemoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddMemoryFragmentViewModel by viewModels {
        AddMemoryFragmentViewModel.provideFactory((activity?.application as TravelFavsApplication).memoriesRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMemoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dropdownTravelTypeSetup()

        sliderMoodSetup()

        datePickerSetup()
        setupAddMemoryButton()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMemoryParams() {
        with(binding) {
            viewModel.createMemoryObject(
                Memory(
                    placeName = tietDestName.text.toString(),
                    date = tvDatePickerResult.text.toString(),
                    placeLocation = "",
                    travelType = autocompleteTv.text.toString(),
                    travelMood = tvSlider.text.toString(),
                    travelNotes = travelNotes.text.toString(),
                    favourite = false
                )

            )

        }
    }

    private fun setupAddMemoryButton() {
        val addButton = binding.addMemoryButton
        addButton.setOnClickListener {
            getMemoryParams()
        }
    }

    private fun dropdownTravelTypeSetup() {
        val items = resources.getStringArray(R.array.travel_types)
        val adapter = ArrayAdapter(requireActivity(), R.layout.list_item, items)
        binding.autocompleteTv.setAdapter(adapter)
    }

    private fun sliderMoodSetup() {
        binding.slider.addOnChangeListener { _, value, _ ->
            binding.tvSlider.text = when (value.toInt()) {
                0 -> getString(R.string.mood_sad)
                10 -> getString(R.string.mood_upset)
                20 -> getString(R.string.mood_happy)
                30 -> getString(R.string.mood_excited)
                40 -> getString(R.string.mood_very_excited)
                else -> ""
            }
        }
    }

    private fun datePickerSetup() {
        binding.datePicker.setOnDateChangedListener { _, year, month, day ->
            binding.tvDatePickerResult.text = convertDatePickerParamsToDate(
                year = year, month = month, day = day
            )
        }

    }
}