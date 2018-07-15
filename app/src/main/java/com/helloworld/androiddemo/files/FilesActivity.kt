package com.helloworld.androiddemo.files

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.helloworld.androiddemo.R
import com.helloworld.androiddemo.toast
import kotlinx.android.synthetic.main.activity_files.*
import java.io.File

class FilesActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)
        createFilesHandler()
        createPreferencesHandler()
    }

    private val preferenceKey = "preference_demo"
    private fun createPreferencesHandler()
    {
        buttonSaveToPreferences.setOnClickListener {
            val editor = getPreferences(Context.MODE_PRIVATE).edit()
            editor.putString(preferenceKey, textPreferences.text.toString())
            editor.apply()
            textPreferences.text.clear()
            toast("Saved preference \"$preferenceKey\"")
        }
        buttonReadPreferences.setOnClickListener {
            val preferences = getPreferences(Context.MODE_PRIVATE)
            val data = preferences.getString(preferenceKey, "")
            textPreferences.setText(data)
            if (data.isEmpty())
            {
                toast("No text found in preferences.")
            }
            else
            {
                toast("Read preference from \"$preferenceKey\"")
            }
        }
    }

    private val fileName = "file_demo.txt"
    private fun createFilesHandler()
    {
        buttonSaveToFile.setOnClickListener {
            val file = File(filesDir, fileName)
            file.writeText(textFile.text.toString())
            textFile.text.clear()
            toast("Saved to $fileName.")
        }
        buttonReadFile.setOnClickListener {
            val file = File(filesDir, fileName)
            val text = file.readText()
            textFile.setText(text)
            if (text.isEmpty())
            {
                toast("No text found in file.")
            }
            else
            {
                toast("Read file from $fileName.")
            }
        }
    }
}
