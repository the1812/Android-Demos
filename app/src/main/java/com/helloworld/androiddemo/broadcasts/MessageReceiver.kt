package com.helloworld.androiddemo.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.telephony.SmsMessage

class MessageReceiver : BroadcastReceiver()
{
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?)
    {
        if (intent != null)
        {
            val dataBundle = intent.extras.get("pdus") as Array<*>
            val messages = dataBundle.map {
                SmsMessage.createFromPdu(it as ByteArray, "3gpp")
            }
            val sender = messages.first().originatingAddress
            val text = messages
                    .map { it.messageBody }
                    .reduce { acc, s -> acc + s }
            onNewMessage(sender, text)
            if (blockMessage)
            {
                abortBroadcast()
            }
        }
    }
    public var onNewMessage: (String, String) -> Unit = { _, _ -> }
    public var blockMessage = false
}