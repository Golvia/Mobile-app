package com.golvia.ng.ui.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.golvia.ng.presentation.navigation.SplashNavigation
import com.golvia.ng.ui.auth.ForgotPasswordScreen
import com.golvia.ng.ui.auth.LoginScreen
import com.golvia.ng.ui.auth.OTPScreen
import com.golvia.ng.ui.auth.ProfileTypeScreen
import com.golvia.ng.ui.auth.RegisterScreen

/**
 * davidsunday
 */

@Composable
internal fun SplashNav(
    navigateToMain: () -> Unit
){
    val navigator = rememberNavController()

    NavHost(
        startDestination = SplashNavigation.ProfileType.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ){
        composable(route = SplashNavigation.Splash.route) {
            SplashScreen(
                navigateToMain = navigateToMain,
                navigateToLogin = {
                    navigator.popBackStack()
                    navigator.navigate(SplashNavigation.Login.route)
                })
        }

        composable(route = SplashNavigation.Login.route) {
            LoginScreen(
                navigateToMain = navigateToMain, navigateToRegister = {
                    navigator.navigate(SplashNavigation.Register.route)
                },
                navigateToForgotPassword = {
                    navigator.navigate(SplashNavigation.ForgetPassword.route)
                }
            )
        }

        composable(route = SplashNavigation.Register.route) {
            RegisterScreen(
                navigateToOtp = { navigator.navigate(SplashNavigation.OTPScreen.route) },
                popUp = {
                    navigator.popBackStack()
                }
            )
        }

        composable(route = SplashNavigation.ForgetPassword.route) {
            ForgotPasswordScreen(
                 popUp = {
                    navigator.popBackStack()
                }
            )
        }

        composable(route = SplashNavigation.OTPScreen.route) {
            OTPScreen(
                popBackStack = {
                    navigator.popBackStack()
                },
                navigateToMain = { navigator.navigate(SplashNavigation.ProfileType.route) }
            )
        }

        composable(route = SplashNavigation.ProfileType.route) {
            ProfileTypeScreen(
                popUp = {
                    //todo, show warning and take the user back to register screen
                    navigator.popBackStack()
                }
            )
        }
    }

}