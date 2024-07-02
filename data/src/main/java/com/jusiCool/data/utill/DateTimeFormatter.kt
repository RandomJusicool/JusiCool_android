package com.jusiCool.data.utill

import android.annotation.SuppressLint
import com.jusiCool.domain.util.exception.NeedLoginException
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date {
    kotlin.runCatching {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this)!!
    }.onSuccess {
        return it
    }
    throw NeedLoginException()
}

@SuppressLint("SimpleDateFormat")
fun Long.toJusiCoolDate(): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(this).toDate()
}