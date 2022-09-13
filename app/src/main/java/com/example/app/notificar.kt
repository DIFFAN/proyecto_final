package com.example.app

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class notificar : AppCompatActivity() {

    private val channelName = "channelName"
    private val channelId = "channelId"
    val noty: Button = findViewById(R.id.noty);

    private lateinit var notificationCustomStyle: Notification
    private val notificationCustomStyleID = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificar)

        buildNotificationCustomStyle()
        val notificationManager = NotificationManagerCompat.from(this)

        noty.setOnClickListener {
            notificationManager.notify(notificationCustomStyleID, notificationCustomStyle)
        }


    }
    private fun buildNotificationCustomStyle() {
        val notificationLayout = RemoteViews(packageName, R.layout.notification_small)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.notification_epanded)

        notificationCustomStyle = NotificationCompat.Builder(this, channelId).also {
            it.setSmallIcon(R.drawable.ic_stat_notification)
            //it.setStyle(NotificationCompat.DecoratedCustomViewStyle())
            it.setCustomContentView(notificationLayout)
            it.setCustomBigContentView(notificationLayoutExpanded)
        }.build()
    }
    private fun buttonsListener() {

    }
}