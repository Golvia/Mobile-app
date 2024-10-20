package com.golvia.ng.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.golvia.ng.presentation.component.CircularImageView
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.OutlinedRoundedButtonWithNoIcon
import com.golvia.ng.presentation.component.SearchBox
import com.golvia.ng.presentation.component.Spacer_12dp
import com.golvia.ng.presentation.component.Spacer_16dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.theme.gold_color
import com.golvia.ng.presentation.theme.light_gold
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.go_premium
import golvia.shared.generated.resources.ic_logo
import golvia.shared.generated.resources.sample_profile_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun HomeScreen(
    navigateToComment: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
){

    val searchValue = remember { mutableStateOf("") }

    DefaultScreenUI(
        onRemoveHeadFromQueue = { },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp),
                        clip = false
                    )
                    .background(Color.White)
            ) {

                Row(
                    modifier = Modifier
                        .padding(top = 42.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchBox(
                        value = searchValue.value,
                        onValueChange = { searchValue.value = it },
                        onSearchExecute = { },
                        modifier = Modifier.weight(0.7f)
                    )

                    Spacer_12dp()

                    OutlinedRoundedButtonWithNoIcon(
                        textButton = stringResource(Res.string.go_premium),
                        textColor = gold_color,
                        borderColor = gold_color,
                        containerColor = light_gold,
                        enabled = true,
                        modifier = Modifier.weight(0.25f)
                    ) {
                        // Todo navigate to premium screen
                    }

                    Spacer_12dp()

                    CircularImageView(
                        image = painterResource(Res.drawable.sample_profile_image),
                        borderColor = LightGray,
                        borderWidth = 0.5.dp,
                        size = 40.dp,
                        modifier = Modifier.weight(0.12f)
                    )
                }
            }
        }
    }

}
