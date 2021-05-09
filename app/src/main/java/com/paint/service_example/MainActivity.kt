package com.paint.service_example

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var serviceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

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

    private fun startService() {
      // val intent = Intent(this@MainActivity, SimpleService::class.java)
      // //startService(intent)
      // ContextCompat.startForegroundService(this, intent)

       // val serviceIntent = Intent(this@MainActivity, SimpleIntentService::class.java)
       // ContextCompat.startForegroundService(this, serviceIntent)

        val jobIntent = Intent(this@MainActivity, SimpleJobIntentService::class.java)
        SimpleJobIntentService.enqueueWork(this, jobIntent)
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

    companion object{
        const val TAG = "MainActivity"
    }
}