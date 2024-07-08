package com.jusiCool.presentation.utill

fun formatCommunityDate(inputDate: String) = inputDate
    .replace("-", ".")
    .replace(" ", "일 ")