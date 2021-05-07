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

    private fun startService() {
        val intent = Intent(this@MainActivity, SimpleService::class.java)
        //startService(intent)
        ContextCompat.startForegroundService(this, intent)
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
}