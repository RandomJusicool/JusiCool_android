package com.jusiCool.presentation.utill

fun String.checkEmailRegex(): Boolean {
    val emailRegex = "dd"
    return this.matches(emailRegex.toRegex())
}

fun String.checkPasswordRegex(): Boolean {
    val passwordRegex = "dd"
    return this.matches(passwordRegex.toRegex())
}