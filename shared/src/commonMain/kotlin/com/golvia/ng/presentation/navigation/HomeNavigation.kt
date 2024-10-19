package com.golvia.ng.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class HomeNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Search : HomeNavigation(
        route = "Search",
        arguments = listOf(
            navArgument("category_id") {
                type = NavType.IntType
            },
            navArgument("sort") {
                type = NavType.IntType
            },
        )
    )

    data object Home : HomeNavigation(route = "Home", arguments = emptyList())

}

