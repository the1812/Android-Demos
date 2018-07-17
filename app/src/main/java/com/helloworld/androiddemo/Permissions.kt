package com.helloworld.androiddemo

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class Permissions(val context: Activity)
{
    private var code = 1
    private val callbackMap = mutableMapOf<Int, () -> Unit>()
    fun request(permissions: Array<String>, callback: () -> Unit)
    {
        if (!permissions.all { ContextCompat.checkSelfPermission(context, it) == 0 })
        {
            ActivityCompat.requestPermissions(context, permissions, code)
            callbackMap[code] = callback
            code++
        }
        else
        {
            callback()
        }
    }
    fun callback(code: Int, permissions: Array<out String>, results: IntArray)
    {
        if (results.count() > 0 && results.first() == 0)
        {
            callbackMap[code]?.invoke()
        }
    }
}