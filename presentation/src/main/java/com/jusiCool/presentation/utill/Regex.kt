package com.jusiCool.presentation.utill

fun String.checkEmailRegex(): Boolean {
    val emailRegex = ".*@.*"
    return this.matches(emailRegex.toRegex())
}

fun String.checkPasswordRegex(): Boolean {
    val passwordRegex = "\"\"^(?=.*[A-Za-z])(?=.*\\d|(?=.*[!@#\\\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])).{8,}\$\"\""
    return this.matches(passwordRegex.toRegex())
}