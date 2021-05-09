package com.paint.service_example

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService

class SimpleJobIntentService: JobIntentService() {

    companion object{
        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, SimpleJobIntentService::class.java, 123, work)
        }
    }

    override fun onCreate() {
        Log.d("TAG", "onCreate()")
        super.onCreate()
    }

    override fun onHandleWork(intent: Intent) {
        Log.d("TAG", "onHandleWork")

        for(i in 0..20){
            Log.d("TAG", "SystemClock.sleep $i")
            SystemClock.sleep(1_000)
        }
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestroy()")
        super.onDestroy()
    }

    override fun onStopCurrentWork(): Boolean {
        Log.d("TAG", "onStopCurrentWork()")
        return super.onStopCurrentWork()
    }
}