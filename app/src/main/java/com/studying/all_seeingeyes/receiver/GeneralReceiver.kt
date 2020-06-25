package com.studying.all_seeingeyes.receiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import com.studying.all_seeingeyes.arsenal.showNotification

class GeneralReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            "android.net.wifi.WIFI_STATE_CHANGED" -> {
                val wifiState =
                    intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
                showNotification(context).showNotification(
                    if (wifiState == WifiManager.WIFI_STATE_ENABLED) "Wi-fi torn on"
                    else "Wi-fi torn off"
                )
            }
            "android.bluetooth.adapter.action.STATE_CHANGED" -> {
                val bluetoothState =
                    intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                showNotification(context).showNotification(
                    if (bluetoothState == BluetoothAdapter.STATE_ON) "Bluetooth torn on"
                    else "Bluetooth torn off"
                )
            }
            "android.intent.action.AIRPLANE_MODE" -> {
                showNotification(context).showNotification(
                    if (airPlaneModeState(context)) "Air plane mode torn on"
                    else "Air plane mode torn off"
                )
            }
            else -> ""
        }

    }

    private fun airPlaneModeState(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val air =
                Settings.System.getInt(context.contentResolver, Settings.System.AIRPLANE_MODE_ON, 0)
            air != 0
        } else {
            Settings.Global.getInt(
                context.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON,
                0
            ) != 0
        }
    }
}
