package com.simplealarm

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val BROADCAST_REQUEST_CODE = 1234

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initListeners()

        updateAlarmStatus()
    }

    private fun initListeners() {
        ctaCheckAlarm.setOnClickListener {
            updateAlarmStatus()
        }

        ctaSetAlarm.setOnClickListener {
            scheduleAlarm(MainActivity@this)
        }
    }

    private fun updateAlarmStatus() {
        if (isAlarmSetted(this, Intent(this, AlarmReceiver::class.java), BROADCAST_REQUEST_CODE)) {
            alarmStatus.text = getString(R.string.alarm_setted)
            Log.d("MainActivity", "========> alarm_setted")
        } else {
            alarmStatus.text = getString(R.string.alarm_not_setted)
            Log.d("MainActivity", "========> alarm_not_setted")
        }
    }

    fun scheduleAlarm(c: Context) {
        val pendingIntent = PendingIntent.getBroadcast(c, BROADCAST_REQUEST_CODE,
                Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        // create alarm to be triggered in 5 minutes
        setAlarm(c, System.currentTimeMillis() + (5 * 60 * 1000), pendingIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
