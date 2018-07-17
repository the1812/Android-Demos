package com.helloworld.androiddemo.services

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
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
    private fun withinContext(func:(MultithreadingActivity) -> Unit)
    {
        val context = this.context.get()
        if (context != null)
        {
            func(context)
        }
    }
    override fun onPreExecute()
    {
        startTime = Calendar.getInstance().time
        withinContext {
            it.progressBar.progress = 0
        }
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
            Thread.sleep(1000)
            time = getTime()
            publishProgress()
        }
    }
    override fun onProgressUpdate(vararg values: Unit?)
    {
        withinContext {
            val progressBar = it.progressBar
            if (progressBar.progress < progressBar.max)
            {
                progressBar.progress++
            }
        }
    }
    override fun onPostExecute(result: Unit?)
    {
        withinContext {
            val dialog = AlertDialog.Builder(it)
            dialog.setTitle("Complete")
            dialog.setMessage("The background thread waited for 5 seconds.")
            dialog.setCancelable(false)
            dialog.setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            dialog.show()
            it.buttonStart.isEnabled = true
        }
    }
}