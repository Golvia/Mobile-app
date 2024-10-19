package com.golvia.ng.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.golvia.ng.presentation.navigation.HomeNavigation
import com.golvia.ng.ui.main.comment.CommentScreen
import com.golvia.ng.ui.main.profile.ProfileScreen
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
                })
        }

        composable(route = HomeNavigation.Comment.route){
            CommentScreen()
        }

        composable(route = HomeNavigation.Profile.route){
            ProfileScreen()
        }
    }
}