package com.studying.all_seeingeyes.arsenal

import android.app.Notification
import android.content.Context

class NotificationPreOreo(context: Context) : NotificationAbstract(context) {


    override fun showNotification(text: String): Notification {
        @Suppress("DEPRECATION")
        val builder = Notification.Builder(this)
        val notification = prepare(builder, text)
        nm.notify(notificationId, notification)
        return notification
    }
}