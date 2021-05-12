package com.paint.service_example

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import com.paint.service_example.App.Companion.CHANNEL_ID

class SimpleService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, " onStartCommand")

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = NotificationCompat
            .Builder(this, CHANNEL_ID)
            .setSubText("Subtext")
            .setContentTitle("Title")
            .setContentInfo("Info")
            .setContentText("Text")
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_launcher_background, "Action", pendingIntent)
            .build()

        startForeground(11, notification)

        val mediaPlayer = MediaPlayer.create(this, R.raw.demo)
        Handler(Looper.getMainLooper()).postDelayed({
            mediaPlayer.start()
        }, 2500)

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return null
    }

    override fun onRebind(intent: Intent?) {
        Log.d(TAG, "onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    companion object {
        const val TAG = "SimpleService"
    }
}