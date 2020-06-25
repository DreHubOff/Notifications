package com.studying.all_seeingeyes.arsenal

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.studying.all_seeingeyes.MyService
import com.studying.all_seeingeyes.R
import com.studying.all_seeingeyes.ui.MainActivity


const val notificationId = 1000010

abstract class NotificationAbstract(context: Context) : ContextWrapper(context) {
    val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    abstract fun showNotification(text: String): Notification

    fun prepare(builder: Notification.Builder, text: String): Notification {
        val notificationIntent = Intent(this, MyService::class.java)
        val contentIntent: PendingIntent = PendingIntent.getActivity(
            this,
            100001,
            notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        builder.apply {
            setContentIntent(contentIntent)
            setContentTitle("hi! I know that ...")
            setContentText(text)
            setAutoCancel(true)
            setSmallIcon(R.mipmap.ic_smile_red_round)
        }
        return builder.build()
    }

}