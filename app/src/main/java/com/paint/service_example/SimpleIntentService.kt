package com.paint.service_example

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

class SimpleIntentService: IntentService("Intent Service name") {

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate()")

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = NotificationCompat
            .Builder(this, App.CHANNEL_ID)
            .setSubText("Subtext")
            .setContentTitle("Title")
            .setContentInfo("Info")
            .setContentText("Text")
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_launcher_background, "Action", pendingIntent)
            .build()

        startForeground(11, notification)
    }

    override fun onHandleIntent(p0: Intent?) {
        Log.d("TAG", "onHandleIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy()")
    }
}