package com.golvia.ng.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import com.golvia.ng.common.Context
import com.golvia.ng.di.appModule
import com.golvia.ng.presentation.navigation.AppNavigation
import com.golvia.ng.presentation.navigation.HomeNavigation
import com.golvia.ng.ui.main.MainNav
import com.golvia.ng.ui.main.home.HomeNav
import com.golvia.ng.ui.splash.SplashNav
import org.koin.compose.KoinApplication
import presentation.theme.AppTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun App(context: Context) {

    KoinApplication(application = {
        modules(appModule(context))
    }) {


        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(NetworkFetcher.Factory())
                }
                .build()
        }

        AppTheme {
            val navigator = rememberNavController()

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {
                NavHost(
                    navController = navigator,
                    startDestination = AppNavigation.Splash.route,
                    modifier = Modifier.fillMaxSize()
                ){
                    composable(route = AppNavigation.Splash.route){
                        SplashNav(navigateToMain = {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Main.route)
                        })
                    }

                    composable(route = HomeNavigation.Home.route){
                        HomeNav(logout = {})
                    }

                    composable(route = AppNavigation.Main.route) {
                        MainNav {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Splash.route)
                        }
                    }
                }
            }

        }
    }
}




