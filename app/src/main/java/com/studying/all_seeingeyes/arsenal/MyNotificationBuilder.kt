package com.studying.all_seeingeyes.arsenal

import android.content.Context
import android.os.Build

fun showNotification(context: Context) =
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        NotificationPreOreo(context)
    } else {
        NotificationOreo(context)
    }

