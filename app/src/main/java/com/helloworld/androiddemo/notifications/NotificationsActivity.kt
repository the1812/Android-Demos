package com.helloworld.androiddemo.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.View
import com.helloworld.androiddemo.R
import com.helloworld.androiddemo.toast
import kotlinx.android.synthetic.main.activity_notifications.*

class NotificationsActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        buttonSendNotification.setOnClickListener {
            val builder = NotificationCompat.Builder(this, "")
            val icon = packageManager.getApplicationInfo("com.helloworld.androiddemo", PackageManager.GET_META_DATA).icon
            builder.setSmallIcon(icon)
                    .setTicker("Notification from Android Demo!")
                    .setContentTitle("Notification")
                    .setContentText("This is a notification").priority = NotificationCompat.PRIORITY_DEFAULT
            NotificationManagerCompat.from(this).notify(1, builder.build())
            buttonUpdateNotification.setOnClickListener {
                NotificationManagerCompat.from(this).notify(1, builder.setContentText("Updated notification").build())
            }
            buttonUpdateNotification.visibility = View.VISIBLE
            buttonRemoveNotification.setOnClickListener {
                NotificationManagerCompat.from(this).cancel(1)
                toast("Notification removed.")
                buttonUpdateNotification.visibility = View.GONE
                buttonRemoveNotification.visibility = View.GONE
            }
            buttonRemoveNotification.visibility = View.VISIBLE
        }
    }
}
