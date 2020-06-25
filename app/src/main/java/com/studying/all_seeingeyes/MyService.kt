package com.studying.all_seeingeyes

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.studying.all_seeingeyes.arsenal.notificationId
import com.studying.all_seeingeyes.arsenal.showNotification
import com.studying.all_seeingeyes.receiver.GeneralReceiver

class MyService : Service() {

    private val generalReceiver = GeneralReceiver()

    override fun onCreate() {
        val intentFilter = IntentFilter("android.intent.action.AIRPLANE_MODE")
        registerReceiver(generalReceiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(notificationId, showNotification(this).showNotification("Start working"))
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        unregisterReceiver(generalReceiver)
    }

}
