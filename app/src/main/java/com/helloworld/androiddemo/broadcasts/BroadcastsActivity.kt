package com.helloworld.androiddemo.broadcasts

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_broad_casts.*

class BroadcastsActivity : AppCompatActivity()
{
    private val intentFilter = IntentFilter()
    private val networkChangeReceiver = NetworkChangeReceiver()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_casts)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
        buttonSend.setOnClickListener {
            val intent = Intent("com.helloworld.androiddemo.CUSTOM_BROADCAST")
            sendBroadcast(intent)
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }
}
