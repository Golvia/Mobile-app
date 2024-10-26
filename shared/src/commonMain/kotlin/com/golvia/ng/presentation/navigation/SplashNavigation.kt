package com.golvia.ng.presentation.navigation

import androidx.navigation.NamedNavArgument

/**
 * davidsunday
 */

sealed class SplashNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {

    data object Splash : SplashNavigation(route = "Splash", arguments = emptyList())

    data object Login : SplashNavigation(route = "Login", arguments = emptyList())

    data object Register : SplashNavigation(route = "Register", arguments = emptyList())

    data object ForgetPassword : SplashNavigation(route = "ForgetPassword", arguments = emptyList())

    data object OTPScreen : SplashNavigation(route = "OTPScreen", arguments = emptyList())

    data object ProfileType : SplashNavigation(route = "ProfileType", arguments = emptyList())

}