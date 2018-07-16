package com.helloworld.androiddemo.files

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.helloworld.androiddemo.R
import com.helloworld.androiddemo.toast
import kotlinx.android.synthetic.main.activity_sql_details.*

class SqlDetailsActivity : AppCompatActivity()
{
    private val databaseHelper = SqlDatabase(this, databaseName, null, 1)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_details)
        val book = intent.extras.get("book") as? Book
        val id = intent.getIntExtra("id", -1)
        if (book != null && id != -1)
        {
            val database = databaseHelper.writableDatabase
            textName.setText(book.name)
            textAuthor.setText(book.author)
            textPages.setText(book.pages.toString())
            textPrice.setText(book.price.toString())
            buttonDelete.setOnClickListener {
                database.delete(tableName, "id = ?", arrayOf(id.toString()))
                toast("Book deleted.")
                finish()
            }
            buttonSave.setOnClickListener {
                val content = ContentValues()
                content.put(Book::name.name, textName.text.toString())
                content.put(Book::author.name, textAuthor.text.toString())
                content.put(Book::pages.name, textPages.text.toString().toInt())
                content.put(Book::price.name, textPrice.text.toString().toDouble())
                database.update(tableName, content, "id = ?", arrayOf(id.toString()))
                toast("Book saved.")
            }
        }
        else
        {
            buttonDelete.visibility = View.GONE
            buttonSave.visibility = View.GONE
            toast("Couldn't get book data.")
        }
    }
}
