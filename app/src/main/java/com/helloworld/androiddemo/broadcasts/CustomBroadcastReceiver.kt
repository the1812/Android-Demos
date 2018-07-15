package com.helloworld.androiddemo.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.helloworld.androiddemo.toast

class CustomBroadcastReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {
        context?.toast("Custom broadcast received!")
    }

}