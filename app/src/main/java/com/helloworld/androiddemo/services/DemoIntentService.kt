package com.helloworld.androiddemo.services

import android.app.IntentService
import android.content.Intent
import android.os.Message
import android.os.Messenger
import com.helloworld.androiddemo.toast

class DemoIntentService : IntentService(DemoIntentService::class.java.name)
{
    override fun onHandleIntent(intent: Intent?)
    {
        if (intent != null)
        {
            fun sendToast(text: String)
            {
                val messenger = intent.extras["messenger"] as Messenger
                val message = Message.obtain()
                message.data.putString("text", text)
                messenger.send(message)
            }
            sendToast("Service started.")
            Thread.sleep(5000)
            val result = (1..100).sum()
            sendToast("Result = $result")
            // Thread.sleep(1000)
            sendToast("Service destroyed.")
        }
    }
}