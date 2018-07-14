package com.helloworld.androiddemo.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_components.*

class ComponentsActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_components)
        buttonAlertDialog.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Dialog")
            dialog.setMessage("This is AlertDialog.")
            dialog.setCancelable(false)
            dialog.setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            dialog.show()
        }
    }
}
