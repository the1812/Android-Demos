package com.helloworld.androiddemo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_send_data.*

class SendDataActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_data)
        val data = intent.getIntExtra("data", -1)
        if (data == -1)
        {
            textData.text = getString(R.string.no_data)
        }
        else
        {
            textData.text = getString(R.string.receive_data)
            textDataValue.text = data.toString()
        }

    }
}
