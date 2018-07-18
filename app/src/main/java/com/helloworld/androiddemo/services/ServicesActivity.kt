package com.helloworld.androiddemo.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Messenger
import com.helloworld.androiddemo.R
import com.helloworld.androiddemo.toast
import kotlinx.android.synthetic.main.activity_services.*
import kotlin.concurrent.thread

class ServicesActivity : AppCompatActivity()
{
    private var binder: DemoService.DemoServiceBinder? = null
    private val connection = object : ServiceConnection
    {
        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder)
        {
            binder = iBinder as DemoService.DemoServiceBinder
        }
        override fun onServiceDisconnected(name: ComponentName?)
        {
            binder = null
        }
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        buttonStart.setOnClickListener {
            val intent = Intent(this, DemoService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
            thread {
                while (binder?.getProgress() ?: 0 < 5)
                {
                    Thread.sleep(1000)
                }
                runOnUiThread {
                    val result = binder?.getResult()?.toString()
                    if (result != null)
                    {
                        toast("Result = $result")
                    }
                    unbindService(connection)
                }
            }
        }
        buttonStartIntent.setOnClickListener {
            val intent = Intent(this, DemoIntentService::class.java)
            intent.putExtra("messenger", Messenger(Handler { message ->
                toast(message.data.getString("text"))
                true }))
            startService(intent)
        }
    }
}
