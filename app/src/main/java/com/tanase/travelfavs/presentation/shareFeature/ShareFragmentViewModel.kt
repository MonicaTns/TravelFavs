package com.tanase.travelfavs.presentation.shareFeature

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel


class ShareFragmentViewModel : ViewModel() {
    fun shareTravelMemory(context: Context) {
        val message = "I'm sharing my travel memory with friends! 🌍✈️"

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)

        if (shareIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(shareIntent)
            Log.d("ShareViewModel", "Share intent started successfully")
        } else {
            Log.e("ShareViewModel", "No app available to handle the share intent")
        }
    }
}