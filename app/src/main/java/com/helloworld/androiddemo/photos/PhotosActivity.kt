package com.helloworld.androiddemo.photos

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.helloworld.androiddemo.Permissions
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_photos.*
import java.io.File

class PhotosActivity : AppCompatActivity()
{
    private lateinit var permissions: Permissions
    private val fileName = "photo.jpg"
    private val camera = 1
    private var cameraUri = Uri.EMPTY
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        permissions = Permissions(this)
        buttonCamera.setOnClickListener {
            permissions.request(
                    arrayOf(Manifest.permission.CAMERA)
            ) {
                val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
                if (file.exists())
                {
                    file.delete()
                }
                file.createNewFile()

                cameraUri = FileProvider.getUriForFile(this, "com.helloworld.androiddemo.FileProvider", file)
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                startActivityForResult(intent, camera)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        when (requestCode)
        {
            camera -> {
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(cameraUri))
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        this.permissions.callback(requestCode, permissions, grantResults)
    }
}
