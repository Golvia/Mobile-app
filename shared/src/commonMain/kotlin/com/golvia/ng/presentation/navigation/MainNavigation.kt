package com.golvia.ng.presentation.navigation

import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.cart
import golvia.shared.generated.resources.cart_border
import golvia.shared.generated.resources.heart2
import golvia.shared.generated.resources.heart_border2
import golvia.shared.generated.resources.home
import golvia.shared.generated.resources.home_border
import golvia.shared.generated.resources.profile
import golvia.shared.generated.resources.profile_border
import org.jetbrains.compose.resources.DrawableResource

/**
 * davidsunday
 */

sealed class MainNavigation (
    val route: String,
    val title: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
) {

    data object Home : MainNavigation(
        route = "Home", title = "Home",
        selectedIcon = Res.drawable.home,
        unSelectedIcon = Res.drawable.home_border
    )

    data object Wishlist : MainNavigation(
        route = "Wishlist", title = "Wishlist",
        selectedIcon = Res.drawable.heart2,
        unSelectedIcon = Res.drawable.heart_border2
    )

    data object Cart : MainNavigation(
        route = "Cart", title = "Cart",
        selectedIcon = Res.drawable.cart,
        unSelectedIcon = Res.drawable.cart_border
    )

    data object Profile : MainNavigation(
        route = "Profile", title = "Profile",
        selectedIcon = Res.drawable.profile,
        unSelectedIcon = Res.drawable.profile_border
    )


}