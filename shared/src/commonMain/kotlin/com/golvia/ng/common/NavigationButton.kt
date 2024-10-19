package com.golvia.ng.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * davidsunday
 */

@Composable
expect fun NavigationButton(
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit
)