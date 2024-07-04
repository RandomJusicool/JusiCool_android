package com.jusiCool.presentation.utill

fun Int.formatStockPriceSign(): String {
    return if (this > 0) {
        "+%,d".format(this)
    } else {
        "%,d".format(this)
    }
}