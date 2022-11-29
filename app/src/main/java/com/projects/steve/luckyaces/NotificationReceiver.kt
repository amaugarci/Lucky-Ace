package com.projects.steve.luckyaces



import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import java.util.*
import android.app.NotificationChannel
import android.net.Uri
import android.media.AudioAttributes
import android.media.Ringtone
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import sun.jvm.hotspot.utilities.IntArray






class NotificationService : IntentService("NotificationService") {
    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000

    @SuppressLint("NewApi")
    private fun createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library

            val context = this.applicationContext
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val uri = Uri.parse("android.resource://com.projects.steve.luckyaces/raw/ja3_6secmusic")
//            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            val att = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME, importance)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.parseColor("#e8334a")
            notificationChannel.description = getString(R.string.notification_channel_description)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationChannel.setSound(uri, att)
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    companion object {

        const val CHANNEL_ID = "com.projects.steve.luckyaces.CHANNEL_ID"
        const val CHANNEL_NAME = "Lucky Aces"
    }


    override fun onHandleIntent(intent: Intent?) {

        //Create Channel
        createChannel()


        var timestamp: Long = 0
        if (intent != null && intent.extras != null) {
            timestamp = intent.extras!!.getLong("timestamp")
        }


        if (timestamp > 0) {

            val SHARED_PREF = "sharedPreferences"
            val sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)


            val context = this.applicationContext
            var notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notifyIntent = Intent(this, ChallengeActivity::class.java)

            val title = getString(R.string.notif_title);
            val message = getString(R.string.notif_body);

            notifyIntent.putExtra("title", title)
            notifyIntent.putExtra("message", message)
            notifyIntent.putExtra("notification", true)

            notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp


            val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val res = this.resources
            val uri = Uri.parse("android.resource://com.projects.steve.luckyaces/raw/ja3_6secmusic")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                mNotification = Notification.Builder(this,
                    CHANNEL_ID
                )
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.card_icon)
//                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.card_icon))
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(
                        Notification.BigTextStyle()
                            .bigText(message)
                    )
                    .setContentText(message).build()

                try {
                    val r = RingtoneManager.getRingtone(applicationContext, uri)
                    r.play()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {

                mNotification = Notification.Builder(this)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(title)
                    .setStyle(
                        Notification.BigTextStyle()
                            .bigText(message)
                    )

                    .setSound(uri)
                    .setContentText(message).build()

            }


            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // mNotificationId is a unique int for each notification that you must define
            notificationManager.notify(mNotificationId, mNotification)
        }


    }

}
