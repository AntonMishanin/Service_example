package com.paint.service_example

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat


class SimpleBroadcastReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent?) {
       Log.d("TAG", "onReceive")

        val quote: String
        val `when` = System.currentTimeMillis()
        val notificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(
            context, 0,
            notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // get your quote here
       // quote = doSomeMethod()
        val mNotifyBuilder = NotificationCompat.Builder(
            context
        ).setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My Quotes")
            //.setContentText(quote)
            .setSound(alarmSound)
            .setAutoCancel(true).setWhen(`when`)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.getResources(),
                    R.mipmap.ic_launcher
                )
            )
            .setStyle(NotificationCompat.BigTextStyle().bigText("quote"))
            .setContentIntent(pendingIntent)
            .setVibrate(
                longArrayOf(
                    1000,
                    1000,
                    1000,
                    1000,
                    1000
                )
            ) // Declair VIBRATOR Permission in AndroidManifest.xml
        notificationManager.notify(5, mNotifyBuilder.build())
    }
}