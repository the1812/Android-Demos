package com.helloworld.androiddemo.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.helloworld.androiddemo.toast

class NetworkChangeReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {
        if (context != null)
        {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
            val info = manager.activeNetworkInfo
            if (info != null && info.isAvailable)
            {
                context.toast("Network is available");
            }
            else
            {
                context.toast("Network is NOT available");
            }
        }
    }

}