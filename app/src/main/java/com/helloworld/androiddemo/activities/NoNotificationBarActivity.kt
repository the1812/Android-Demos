package com.helloworld.androiddemo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.helloworld.androiddemo.R

class NoNotificationBarActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_no_notification_bar)
    }
}
