package com.tanase.travelfavs.presentation.contactFeature

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.lifecycle.ViewModel

class ContactUsFragmentViewModel : ViewModel() {
    fun sendEmail(context: Context) {
        val recipientEmail = "monica.tanase299@gmail.com"
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback or Inquiry")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Hello,\n\nI would like to provide the following feedback:\n"
        )

        val activities: List<*> = context.packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )

        if (activities.isNotEmpty()) {
            context.startActivity(intent)
            Log.d("ContactUsViewModel", "Email intent started successfully")
        } else {
            Log.e("ContactUsViewModel", "No email app available")

        }
    }
}