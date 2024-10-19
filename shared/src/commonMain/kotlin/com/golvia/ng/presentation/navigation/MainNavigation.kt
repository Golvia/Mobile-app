package com.golvia.ng.presentation.navigation

import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.home
import golvia.shared.generated.resources.home_selected
import golvia.shared.generated.resources.ic_messages
import golvia.shared.generated.resources.ic_messages_selected
import golvia.shared.generated.resources.ic_network
import golvia.shared.generated.resources.ic_network_selected
import golvia.shared.generated.resources.ic_notification
import golvia.shared.generated.resources.ic_notification_selected
import golvia.shared.generated.resources.ic_settings
import golvia.shared.generated.resources.ic_settings_selected
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
        selectedIcon = Res.drawable.home_selected,
        unSelectedIcon = Res.drawable.home
    )

    data object Network : MainNavigation(
        route = "Network", title = "Network",
        selectedIcon = Res.drawable.ic_network_selected,
        unSelectedIcon = Res.drawable.ic_network
    )

    data object Messages : MainNavigation(
        route = "Messages", title = "Messages",
        selectedIcon = Res.drawable.ic_messages_selected,
        unSelectedIcon = Res.drawable.ic_messages
    )

    data object Notification : MainNavigation(
        route = "Notification", title = "Notification",
        selectedIcon = Res.drawable.ic_notification_selected,
        unSelectedIcon = Res.drawable.ic_notification
    )

    data object Settings : MainNavigation(
        route = "Settings", title = "Settings",
        selectedIcon = Res.drawable.ic_settings_selected,
        unSelectedIcon = Res.drawable.ic_settings
    )


}