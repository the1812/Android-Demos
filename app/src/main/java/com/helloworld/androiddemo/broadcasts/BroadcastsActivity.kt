package com.helloworld.androiddemo.broadcasts

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.helloworld.androiddemo.R

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
    }

    override fun onDestroy()
    {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }
}
