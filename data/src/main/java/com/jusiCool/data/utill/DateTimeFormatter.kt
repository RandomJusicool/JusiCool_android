package com.jusiCool.data.utill

import android.annotation.SuppressLint
import com.jusiCool.domain.util.exception.NeedLoginException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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

fun String.isDateExpired(): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    return kotlin.runCatching {
        val dateTime = LocalDateTime.parse(this, formatter)
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        dateTime.isBefore(currentTime)
    }.getOrElse {
        true
    }
}