package com.helloworld.androiddemo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.helloworld.androiddemo.R

class NoTitleActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_title)
    }
}
