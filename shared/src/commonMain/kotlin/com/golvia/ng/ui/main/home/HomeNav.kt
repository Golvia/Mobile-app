package com.golvia.ng.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.golvia.ng.businessLayer.domain.FeedsData
import com.golvia.ng.businessLayer.domain.createDummyFeedsData
import com.golvia.ng.presentation.navigation.HomeNavigation
import com.golvia.ng.ui.main.comment.CommentScreen
import com.golvia.ng.ui.main.detail.DetailScreen
import com.golvia.ng.ui.main.profile.ProfileScreen
import kotlinx.serialization.json.Json
import org.koin.compose.koinInject

/**
 * davidsunday
 */

@Composable
fun HomeNav(
    logout: () -> Unit
){
    val navigator = rememberNavController()
    NavHost(
        startDestination = HomeNavigation.Home.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ){

        composable(route = HomeNavigation.Home.route) {
            HomeScreen(
                navigateToComment = {
                    navigator.navigate(HomeNavigation.Comment.route)
                },
                navigateToProfile = {
                    navigator.navigate(HomeNavigation.Profile.route)
                },
                onNavigateToDetail = { feedsData ->
                    val route = HomeNavigation.Detail.withFeedsData(feedsData)
                    navigator.navigate(route)
                })
        }

        composable(route = HomeNavigation.Comment.route){
            CommentScreen()
        }

        composable(route = HomeNavigation.Profile.route){
            ProfileScreen()
        }

        composable(
            route = HomeNavigation.Detail.route,
            arguments = HomeNavigation.Detail.arguments
        ) { backStackEntry ->
            val feedsDataJson = backStackEntry.arguments?.getString("feedsData")
            val feedsData = feedsDataJson?.let {
                Json.decodeFromString<FeedsData>(it)
            }

            DetailScreen(
                popBackStack = { navigator.popBackStack() },
                feedsData = feedsData ?: createDummyFeedsData()[0]
            )
        }
    }
}