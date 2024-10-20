package com.golvia.ng.businessLayer.util

/**
 * davidsunday
 */

fun String.urlEncode(): String {
    val encoded = StringBuilder()
    for (char in this) {
        when {
            char.isLetterOrDigit() -> encoded.append(char)
            else -> encoded.append("%${char.code.toString(16).uppercase()}")
        }
    }
    return encoded.toString()
}