package com.helloworld.androiddemo.web

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_web_data.*
import kotlin.concurrent.thread

class WebDataActivity : AppCompatActivity()
{
    private val invalidHint = "URL is invalid"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_data)
        val url = intent.getStringExtra("url")
        if (url.isEmpty())
        {
            textRawData.text = invalidHint
        }
        else
        {
            thread {
                downloadString(url) {
                    runOnUiThread {
                        textRawData.text = it
                    }
                }
            }
        }
    }
}
