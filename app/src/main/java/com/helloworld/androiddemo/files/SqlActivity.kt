package com.helloworld.androiddemo.files

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_sql.*

class SqlActivity : AppCompatActivity()
{
    private val tableName = "Book"
    private val databaseName = "demo_database"
    private val databaseHelper = SqlDatabase(this, databaseName, null, 1)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql)
        readDatabase()
        buttonFill.setOnClickListener {
            val database = databaseHelper.writableDatabase
            val books = arrayOf(
                    Book("The Da Vinci Code", "Dan Brown", 454, 16.96),
                    Book("The Lost Symbol", "Dan Brown", 510, 19.95)
            )
            for (book in books)
            {
                val content = ContentValues()
                content.put(Book::name.name, book.name)
                content.put(Book::author.name, book.author)
                content.put(Book::pages.name, book.pages)
                content.put(Book::price.name, book.price)
                database.insert(tableName, null, content)
            }
            readDatabase()
        }
    }

    private fun readDatabase()
    {
        val database = databaseHelper.readableDatabase
        val cursor = database.query(tableName, null,null,null,null,null,null,null)
        if (cursor.count == 0)
        {
            buttonFill.visibility = View.VISIBLE
            root_list.adapter = null
        }
        else
        {
            buttonFill.visibility = View.GONE
            val list = mutableListOf<Book>()
            cursor.moveToFirst()
            do
            {
                val name = cursor.getString(cursor.getColumnIndex(Book::name.name))
                val author = cursor.getString(cursor.getColumnIndex(Book::author.name))
                val pages = cursor.getInt(cursor.getColumnIndex(Book::pages.name))
                val price = cursor.getDouble(cursor.getColumnIndex(Book::price.name))
                list.add(Book(name, author, pages, price))
            }
            while (cursor.moveToNext())
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list.map { it.name })
            root_list.adapter = adapter
        }
        cursor.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.delete_all_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if (item != null)
        {
            when (item.itemId)
            {
                R.id.menu_item_delete_all -> {
                    val database = databaseHelper.writableDatabase
                    database.delete(tableName, null, null)
                    readDatabase()
                }
            }
        }
        return true;
    }
}
