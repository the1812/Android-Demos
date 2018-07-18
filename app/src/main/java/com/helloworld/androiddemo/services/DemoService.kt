package com.helloworld.androiddemo.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.util.Log
import com.helloworld.androiddemo.toast
import java.util.*

class DemoService : Service()
{
    private var progress = 0
    private var result = 0
    private fun calculateResult()
    {
        for (i in 1..100)
        {
            result += i
        }
    }

    class DemoServiceBinder(private val service: DemoService) : Binder()
    {
        fun getProgress() = service.progress++
        fun getResult() = service.result
    }
    override fun onBind(intent: Intent): DemoServiceBinder
    {
        toast("Service started")
        progress = 0
        calculateResult()
        return DemoServiceBinder(this)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy()
    {
        toast("Service destroyed")
        super.onDestroy()
    }
}