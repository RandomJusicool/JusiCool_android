package com.jusiCool.presentation.utill

fun String.checkEmailRegex(): Boolean {
    val emailRegex = "dd" // 임시 -> 후에 정규화식 작성?
    return this.matches(emailRegex.toRegex())
}

fun String.checkPasswordRegex(): Boolean {
    val passwordRegex = "dd" // 임시 -> 후에 정규화식 작성?
    return this.matches(passwordRegex.toRegex())
}