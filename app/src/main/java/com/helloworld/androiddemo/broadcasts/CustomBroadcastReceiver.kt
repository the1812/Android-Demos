package com.helloworld.androiddemo.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomBroadcastReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {
        Toast.makeText(context, "Custom broadcast received!", Toast.LENGTH_SHORT).show()
    }

}