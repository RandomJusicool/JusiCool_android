package com.jusiCool.presentation.utill

fun String.checkEmailRegex() =
    this.matches("dd".toRegex() /* 임시 -> 후에 정규화식 작성?*/)

fun String.checkPasswordRegex() =
    this.matches("dd".toRegex() /* 임시 -> 후에 정규화식 작성?*/)