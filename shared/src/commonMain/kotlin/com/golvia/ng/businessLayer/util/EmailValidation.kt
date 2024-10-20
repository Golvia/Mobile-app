package com.golvia.ng.businessLayer.util


/**
 * davidsunday
 */


fun validateEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    return email.matches(Regex(emailRegex))
}