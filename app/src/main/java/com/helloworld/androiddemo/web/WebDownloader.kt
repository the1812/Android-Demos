package com.helloworld.androiddemo.web

import java.net.URL

fun downloadString(urlString: String, callback: (string: String) -> Unit)
{
    val url = URL(urlString)
    val text = url.openStream().bufferedReader().readText()
    callback(text)
}
