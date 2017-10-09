package com.simplealarm

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.util.Log

/**
 * Created by kppro on 09/10/2017.
 */
class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("AlarmReceiver", "=========> onReceive")
        if (context == null) return

        createNotification(context, "Simple title", "Simple description...")
    }

    fun createNotification(c: Context, title: String, description: String) {
        val builder = NotificationCompat.Builder(c)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(c.getResources(), R.mipmap.ic_launcher_round))
                .setContentTitle(title)
                .setContentText(description)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_SOUND)
                .setLights(Color.RED, 100, 3000).setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT)

        // launch activity on notification clicked
        val intent = Intent(c, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(c, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setContentIntent(pendingIntent)

        val notifManager = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifManager.notify(4567, builder.build())
    }
}
