package com.example.ch10_test_2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.ch10_test_2.databinding.ActivityMain2Binding
import com.example.ch10_test_2.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val builder: NotificationCompat.Builder

            val title = binding.title.text.toString()
            val msg = binding.msg.text.toString()

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelId="one-channel"
                val channelName="My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "My Channel One Description"
                    setShowBadge(true)
                    val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val audioAttributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                    setSound(uri, audioAttributes)
                    enableVibration(true)
                }
                manager.createNotificationChannel(channel)
                builder = NotificationCompat.Builder(this, channelId)
            }else {
                builder = NotificationCompat.Builder(this)
            }

            builder.run {
                setSmallIcon(R.drawable.small)
                setWhen(System.currentTimeMillis())
                setContentTitle("$title")
                setContentText("$msg")
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
            }

            val KEY_TEXT_REPLY = "key_text_reply"
            var replyLabel = "답장"
            var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }
            val replyIntent = Intent(this, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(
                this, 30, replyIntent, PendingIntent.FLAG_MUTABLE
            )

            builder.addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.send,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )

            manager.notify(11, builder.build())

        }
    }
}