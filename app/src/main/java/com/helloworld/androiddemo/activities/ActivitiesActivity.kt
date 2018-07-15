package com.helloworld.androiddemo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.helloworld.androiddemo.ActivityNavigation
import com.helloworld.androiddemo.R
import com.helloworld.androiddemo.toast
import kotlinx.android.synthetic.main.activity_activities.*
import java.util.*

class ActivitiesActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)
        createList()
        buttonToast.setOnClickListener {
            toast(R.string.toast)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.activities_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if (item != null)
        {
            when (item.itemId)
            {
                R.id.menu_item_1 -> toast(R.string.menu_item_1)
                R.id.menu_item_2 -> toast(R.string.menu_item_2)
            }
        }
        return true;
    }

    private val data = Random().nextInt(10000)
    private fun createList()
    {
        val navigation = ActivityNavigation(this, root_list)
        navigation.items = mapOf(
            getString(R.string.no_title) to NoTitleActivity::class.java,
            getString(R.string.no_notification_bar) to NoNotificationBarActivity::class.java,
            "${getString(R.string.send_data)} ${data.toString()}" to SendDataActivity::class.java
        )
        navigation.beforeStartActivity = { name, intent ->
            if (name.startsWith(getString(R.string.send_data)))
            {
                intent.putExtra("data", data)
            }
        }
        navigation.createNavigation()
    }
}
