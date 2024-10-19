package com.golvia.ng.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.golvia.ng.common.ChangeStatusBarColors
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.navigation.MainNavigation
import com.golvia.ng.presentation.theme.DefaultNavigationBarItemTheme
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.icon_default_color
import com.golvia.ng.ui.main.home.HomeNav
import com.golvia.ng.ui.main.message.MessageNav
import com.golvia.ng.ui.main.network.NetworkNav
import com.golvia.ng.ui.main.notification.NotificationNav
import com.golvia.ng.ui.main.settings.SettingsNav
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@Composable
fun MainNav(
    logout: () -> Unit
){

    val navBottomBarController = rememberNavController()
    ChangeStatusBarColors(Color.White)

    Scaffold(bottomBar = {
        BottomNavigationUI(navController = navBottomBarController)
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                startDestination = MainNavigation.Home.route,
                navController = navBottomBarController,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = MainNavigation.Home.route) {
                    HomeNav(logout = logout)
                }
                composable(route = MainNavigation.Network.route) {
                    NetworkNav()
                }
                composable(route = MainNavigation.Messages.route) {
                    MessageNav()
                }
                composable(route = MainNavigation.Notification.route) {
                    NotificationNav()
                }
                composable(route = MainNavigation.Settings.route) {
                    SettingsNav(logout = logout)
                }
            }
        }

    }

}

@Composable
fun BottomNavigationUI(
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp
        ) {

            Spacer_8dp()

            val items = listOf(
                MainNavigation.Home,
                MainNavigation.Network,
                MainNavigation.Messages,
                MainNavigation.Notification,
                MainNavigation.Settings,
            )
            items.forEach { item ->
                NavigationBarItem(
                    label = {},
                    colors = DefaultNavigationBarItemTheme(),
                    selected = item.route == currentRoute,
                    icon = {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(
                                    if (item.route == currentRoute) MaterialTheme.colorScheme.primary
                                    else Color.Transparent
                                )
                                .padding(horizontal = 4.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painterResource(
                                        if (item.route == currentRoute) item.selectedIcon
                                        else item.unSelectedIcon
                                    ),
                                    contentDescription = item.title,
                                    tint = if (item.route == currentRoute) Color.White else icon_default_color
                                )
                                Text(
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        lineHeight = 12.sp,
                                        fontFamily = LatoTypography().bodySmall.fontFamily
                                    ),
                                    text = item.title,
                                    color = if (item.route == currentRoute) Color.White else icon_default_color.copy(0.7f)
                                )
                            }
                        }
                    },
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
            Spacer_8dp()
        }
    }
}