package com.etpl.demoencora.utils

import java.text.SimpleDateFormat

fun String.getDate():String{
    var date:String?=null
    var sdf = SimpleDateFormat("yyyy-mm-dd").parse(this)
    return  SimpleDateFormat("yyyy-mm-dd").format(sdf)
}