package com.helloworld.androiddemo.files

import java.io.Serializable

data class Book(var name:String, var author:String, var pages:Int, var price:Double)
    : Serializable