package com.golvia.ng.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.Spacer_16dp
import com.golvia.ng.presentation.component.Spacer_24dp
import com.golvia.ng.presentation.component.StepItem
import com.golvia.ng.presentation.theme.light_gray
import com.golvia.ng.presentation.theme.page_text_color2
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.one
import golvia.shared.generated.resources.three
import golvia.shared.generated.resources.two
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@Composable
fun ProfileTypeScreen(
    popUp: () -> Unit
) {
    DefaultScreenUI(
        onRemoveHeadFromQueue = { }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer_24dp()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StepItem(
                    icon = painterResource(Res.drawable.one),
                    title = "Sign up",
                    isActive = true
                )

                HorizontalDivider(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f),
                    thickness = 1.dp, color = light_gray
                )

                StepItem(
                    icon = painterResource(Res.drawable.two),
                    title = "Profile type",
                    color = page_text_color2,
                    isActive = false
                )

                HorizontalDivider(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f),
                    thickness = 1.dp, color = light_gray
                )

                StepItem(
                    icon = painterResource(Res.drawable.three),
                    title = "Registration",
                    color = Color.Gray,
                    isActive = false
                )
            }
        }
    }
}