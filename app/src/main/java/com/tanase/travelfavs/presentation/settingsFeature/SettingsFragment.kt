package com.tanase.travelfavs.presentation.settingsFeature

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tanase.travelfavs.databinding.FragmentSettingsBinding
import com.tanase.travelfavs.presentation.contactFeature.ContactUsFragmentViewModel
import java.util.Locale

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switch = binding.switchDarkMode
        switch.setOnCheckedChangeListener { buttonview, isChecked ->
            if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        val switchButton = binding.switchButtonLanguage

        viewModel.switchStateLiveData.observe(viewLifecycleOwner) { isChecked ->
            switchButton.isChecked = isChecked
            setLocale(if (isChecked) "ro" else "en")
        }

        switchButton.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveSwitchState(isChecked)

            if (isChecked) {
                setLocale("ro")
            } else {
                setLocale("en")
            }
        }

    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale

        requireActivity().resources.updateConfiguration(
            config,
            requireActivity().resources.displayMetrics
        )
    }

}