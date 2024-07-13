package com.jusiCool.presentation.utill

fun Int.formatStockPrice(): String = "%,d".format(this)

fun String.formatStockPrice(): String = this.toInt().formatStockPrice()