package com.helloworld.androiddemo.files

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.helloworld.androiddemo.toast

class SqlDatabase(val context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version)
{
    override fun onCreate(database: SQLiteDatabase?)
    {
        if (database != null)
        {
            val sql = """create table Book (
            id integer primary key autoincrement,
            author text,
            price real,
            pages integer,
            name text)
            """
            database.execSQL(sql)
            context?.toast("Database created.")
        }
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        context?.toast("Database upgraded.")
    }

}