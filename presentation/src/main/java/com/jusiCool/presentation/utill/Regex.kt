package com.jusiCool.presentation.utill

fun String.checkEmailRegex() =
    this.matches(".*@.*".toRegex())

fun String.checkPasswordRegex() =
    this.matches("^(?=.[A-Za-z])(?=.[0-9])|(?=.[A-Za-z])(?=.[^A-Za-z0-9])|(?=.[0-9])(?=.[^A-Za-z0-9]).{8,}$".toRegex())