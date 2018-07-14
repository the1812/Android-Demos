package com.helloworld.androiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.helloworld.androiddemo.activities.ActivitiesActivity
import com.helloworld.androiddemo.broadcasts.BroadcastsActivity
import com.helloworld.androiddemo.components.ComponentsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createList();
    }

    private fun createList()
    {
        val navigation = ActivityNavigation(this, root_list)
        navigation.items = mapOf(
            getString(R.string.activities) to ActivitiesActivity::class.java,
            getString(R.string.components) to ComponentsActivity::class.java,
            getString(R.string.broadcasts) to BroadcastsActivity::class.java
        )
        navigation.createNavigation()
    }
}
