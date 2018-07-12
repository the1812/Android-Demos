package com.helloworld.androiddemo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class ActivityNavigation(private val context: Context, private val list: ListView)
{
    var beforeStartActivity: ((String, Intent) -> Unit)? = null
    var items: Map<String, Class<out AppCompatActivity>> = emptyMap()
    fun createNavigation()
    {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items.keys.toTypedArray())
        list.adapter = adapter
        list.setOnItemClickListener { _, _, i, _ ->
            val activity = items.values.elementAt(i)
            val intent = Intent(context, activity)
            beforeStartActivity?.invoke(items.keys.elementAt(i), intent)
            context.startActivity(intent)
        }
    }
    fun clearNavigation()
    {
        list.adapter = null
        list.onItemClickListener = null
    }
}