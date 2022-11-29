package com.projects.steve.luckyaces

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

//import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val service = Intent(context, NotificationService::class.java)
        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))

        // set
        val SHARED_PREF = "sharedPreferences"
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        sharedPreferences.edit().putBoolean("challengeActive", false).commit()

        context.startService(service)
    }

}