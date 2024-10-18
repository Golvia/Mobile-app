package com.golvia.ng.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.golvia.ng.common.ChangeStatusBarColors

/**
 * davidsunday
 */

@Composable
fun MainNav(
    logout: () -> Unit
){

    val navBottomBarController = rememberNavController()
    ChangeStatusBarColors(Color.White)

}