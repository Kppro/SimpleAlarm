package com.simplealarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

/**
 * Created by kppro on 09/10/2017.
 */


fun setAlarm(c: Context, time: Long, pendingItent: PendingIntent) {
    val am = c.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    if (Build.VERSION.SDK_INT >= 23) {
        // Wakes up the device in Doze Mode
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingItent)
    } else if (Build.VERSION.SDK_INT >= 19) {
        // Wakes up the device in Idle Mode
        am.setExact(AlarmManager.RTC_WAKEUP, time, pendingItent)
    } else {
        // Old APIs
        am.set(AlarmManager.RTC_WAKEUP, time, pendingItent)
    }
}

fun isAlarmSetted(c: Context, intent: Intent, requestCode: Int): Boolean {
    return PendingIntent
            .getBroadcast(c, requestCode, intent, PendingIntent.FLAG_NO_CREATE) != null
}