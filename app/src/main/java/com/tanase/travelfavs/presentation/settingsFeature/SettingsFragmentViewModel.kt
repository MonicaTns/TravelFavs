package com.tanase.travelfavs.presentation.settingsFeature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsFragmentViewModel : ViewModel() {
    val switchStateLiveData = MutableLiveData<Boolean>()

    fun saveSwitchState(isChecked: Boolean) {
        switchStateLiveData.value = isChecked
    }
}