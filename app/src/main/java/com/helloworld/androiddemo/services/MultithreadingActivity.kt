package com.helloworld.androiddemo.services

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_multithreading.*
import java.lang.ref.WeakReference
import java.util.*

class MultithreadingActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multithreading)
        buttonStart.setOnClickListener {
            DialogTask(WeakReference(this)).execute()
            buttonStart.isEnabled = false
        }
    }
}
class DialogTask(val context: WeakReference<MultithreadingActivity>) : AsyncTask<Unit, Unit, Unit>()
{
    private var startTime = Calendar.getInstance().time
    override fun onPreExecute()
    {
        startTime = Calendar.getInstance().time
    }
    override fun doInBackground(vararg params: Unit?)
    {
        fun getTime(): Date
        {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.SECOND, -5)
            return calendar.time
        }
        var time = getTime()
        while (time.before(startTime))
        {
            Log.d("Multithreading", "start: $startTime, current-5: $time")
            Thread.sleep(1000)
            time = getTime()
        }
    }
    override fun onPostExecute(result: Unit?)
    {
        val context = this.context.get()
        if (context != null)
        {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Complete")
            dialog.setMessage("The background thread waited for 5 seconds.")
            dialog.setCancelable(false)
            dialog.setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            dialog.show()
            context.buttonStart.isEnabled = true
        }
    }
}