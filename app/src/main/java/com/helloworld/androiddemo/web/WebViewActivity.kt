package com.helloworld.androiddemo.web

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.helloworld.androiddemo.Permissions
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity()
{
    private lateinit var permissions: Permissions
    private var url = ""
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        permissions = Permissions(this)
        permissions.request(arrayOf(Manifest.permission.INTERNET)) {
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.settings.loadWithOverviewMode = true
            webView.settings.useWideViewPort = true
            webView.webChromeClient = WebChromeClient()
            webView.webViewClient = object : WebViewClient()
            {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean
                {
                    url = request?.url.toString()
                    textAddress.setText(url)
                    view?.loadUrl(url)
                    return true
                }
            }
            textAddress.setOnEditorActionListener { _, _, _ ->
                url = textAddress.text.toString()
                webView.loadUrl(url)
                true
            }
            buttonRaw.setOnClickListener {
                val intent = Intent(this, WebDataActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        this.permissions.callback(requestCode, permissions, grantResults)
    }
}
