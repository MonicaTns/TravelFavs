package com.tanase.travelfavs.presentation.contactFeature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tanase.travelfavs.databinding.FragmentAboutUsBinding
import com.tanase.travelfavs.databinding.FragmentContactUsBinding

class ContactUsFragment:Fragment() {
    private var _binding: FragmentContactUsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ContactUsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactUsBinding.inflate(inflater, container, false)
        binding.contactUsButton.setOnClickListener {
            viewModel.sendEmail(requireActivity())
            Log.d("ContactUsFragment", "Button clicked")
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}