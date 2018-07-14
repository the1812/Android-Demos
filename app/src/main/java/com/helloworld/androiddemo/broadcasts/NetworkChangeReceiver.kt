package com.helloworld.androiddemo.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class NetworkChangeReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {
        if (context != null)
        {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
            val info = manager.activeNetworkInfo
            val toast = { text: String -> Toast.makeText(context, text, Toast.LENGTH_SHORT).show() }
            if (info != null && info.isAvailable)
            {
                toast("Network is available");
            }
            else
            {
                toast("Network is NOT available");
            }
        }
    }

}