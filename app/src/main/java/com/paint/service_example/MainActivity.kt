package com.paint.service_example

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.util.*
import java.util.Calendar.HOUR_OF_DAY


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var serviceConnection: ServiceConnection

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val value = savedInstanceState?.getString("test")
        Log.d(TAG, "onCreate value = $value")

        setAlarm()

        val calendar: Calendar = Calendar.getInstance()
        val hour = calendar.get(HOUR_OF_DAY)
        Log.d(TAG, "hour = $hour")
        startNotification()

        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

            }

            override fun onServiceDisconnected(p0: ComponentName?) {

            }
        }

        val start = findViewById<Button>(R.id.start)
        start.setOnClickListener { startService() }

        val stop = findViewById<Button>(R.id.stop)
        stop.setOnClickListener { stopService() }

        val bind = findViewById<Button>(R.id.bind)
        bind.setOnClickListener { bindService() }

        val unbind = findViewById<Button>(R.id.unbind)
        unbind.setOnClickListener { unbindService() }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState()")
        outState.putString("test", "value")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val value = savedInstanceState.getString("test")
        Log.d(TAG, "onRestoreInstanceState() value = $value")
    }

    private fun startService() {
        val intent = Intent(this@MainActivity, SimpleService::class.java)
        //startService(intent)
        ContextCompat.startForegroundService(this, intent)

        // val serviceIntent = Intent(this@MainActivity, SimpleIntentService::class.java)
        // ContextCompat.startForegroundService(this, serviceIntent)

        //val jobIntent = Intent(this@MainActivity, SimpleJobIntentService::class.java)
        //SimpleJobIntentService.enqueueWork(this, jobIntent)

        //mediaPlayer?.start()
    }

    private fun stopService() {
        val intent = Intent(this@MainActivity, SimpleService::class.java)
        stopService(intent)
    }

    private fun bindService() {
        val intent = Intent(this@MainActivity, SimpleService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
    }

    private fun unbindService() {
        unbindService(serviceConnection)
    }

    private fun startNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = NotificationCompat
            .Builder(this, App.CHANNEL_ID)
            .setSubText("Subtext")
            .setContentTitle("Title")
            .setContentInfo("Info")
            .setContentText("Text")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .addAction(R.drawable.ic_launcher_background, "Action", pendingIntent)
            .build()

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(212121, notification)

        mediaPlayer = MediaPlayer.create(this, R.raw.demo)
    }

    private fun setAlarm() {

        val calendar: Calendar = Calendar.getInstance()
        calendar.set(HOUR_OF_DAY, 19)
        calendar.set(Calendar.MINUTE, 32)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val myIntent = Intent(this, SimpleBroadcastReceiver::class.java)
        val ALARM1_ID = 10000
        val pendingIntent = PendingIntent.getBroadcast(
            this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.getTimeInMillis(),
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    companion object {
        const val TAG = "MainActivity"
    }
}