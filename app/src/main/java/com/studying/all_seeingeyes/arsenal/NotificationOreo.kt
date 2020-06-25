package com.studying.all_seeingeyes.arsenal

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.studying.all_seeingeyes.R


const val CHANNEL_ID_DEFAULT = "com.studying.all_seeingeyes.arsenal.CHANNEL_ID_DEFAULT"


@RequiresApi(Build.VERSION_CODES.O)
class NotificationOreo(context: Context) : NotificationAbstract(context) {

    init {

        val uri = Uri.parse("android.resource://" + this.packageName + "/" + R.raw.karlson)

        val channel = NotificationChannel(
            CHANNEL_ID_DEFAULT,
            "My CHANNEL",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.apply {
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            setShowBadge(true)
            shouldVibrate()
            vibrationPattern = longArrayOf(200, 0, 200, 0, 0, 0, 200,90,79)
            enableVibration(true)
            setSound(
                uri,
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
        }
        nm.createNotificationChannel(channel)

    }

    override fun showNotification(text: String): Notification {
        val builder = Notification.Builder(this, CHANNEL_ID_DEFAULT)
        val notification = prepare(builder, text)
        nm.notify(notificationId, notification)
        return notification
    }
}