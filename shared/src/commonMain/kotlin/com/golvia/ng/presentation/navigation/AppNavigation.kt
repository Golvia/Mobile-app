package com.golvia.ng.presentation.navigation

import androidx.navigation.NamedNavArgument

/**
 * davidsunday
 */

sealed class AppNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {

    data object Splash : AppNavigation(route = "Splash", arguments = emptyList())

    data object Main : AppNavigation(route = "Main", arguments = emptyList())


}