package com.golvia.ng.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.golvia.ng.businessLayer.domain.FeedsData
import com.golvia.ng.businessLayer.util.urlEncode
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
    data object Comment : HomeNavigation(route = "Comment", arguments = emptyList())
    data object Profile : HomeNavigation(route = "Profile", arguments = emptyList())

    data object Detail : HomeNavigation(
        route = "Detail/{feedsData}",
        arguments = listOf(
            navArgument("feedsData") {
                type = NavType.StringType
            }
        )
    ) {
        fun withFeedsData(feedsData: FeedsData): String {
            val json = Json.encodeToString(feedsData)
            return "Detail/${json.urlEncode()}"
        }
    }
}

