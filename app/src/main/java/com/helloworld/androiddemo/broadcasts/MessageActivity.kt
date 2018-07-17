package com.helloworld.androiddemo.broadcasts

import android.Manifest
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import com.helloworld.androiddemo.Permissions
import com.helloworld.androiddemo.R
import com.helloworld.androiddemo.toast
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity()
{
    private val messageReceiver = MessageReceiver()
    private lateinit var permissions: Permissions
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        permissions = Permissions(this)
        permissions.request(arrayOf(Manifest.permission.RECEIVE_SMS)) {
            val filter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
            filter.priority = Int.MAX_VALUE
            registerReceiver(messageReceiver, filter)
            messageReceiver.onNewMessage = { sender, content ->
                textSender.text = sender
                textContent.text = content
                toast("New message arrived.")
            }
            checkBoxBlock.setOnCheckedChangeListener { _, _ ->
                messageReceiver.blockMessage = checkBoxBlock.isChecked
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        this.permissions.callback(requestCode, permissions, grantResults)
    }
    override fun onDestroy()
    {
        super.onDestroy()
        unregisterReceiver(messageReceiver)
    }
}
