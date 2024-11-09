package com.golvia.ng.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.NormalRoundedButton
import com.golvia.ng.presentation.component.SingleSelectionListItem
import com.golvia.ng.presentation.component.Spacer_24dp
import com.golvia.ng.presentation.component.Spacer_64dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.component.StepItem
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.Thick_black
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.presentation.theme.light_gray
import com.golvia.ng.presentation.theme.page_text_color2
import com.golvia.ng.ui.auth.viewModel.AuthViewModel
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.one
import golvia.shared.generated.resources.three
import golvia.shared.generated.resources.two
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

/**
 * davidsunday
 */

@Composable
fun SportTypeScreen(
    popUp: () -> Unit,
    onItemSelected: (String) -> Unit = {},
    onNextClicked: () -> Unit = {}
) {
    val authViewModel: AuthViewModel = koinInject()
    val userName = authViewModel.userName.collectAsState()

    // Keep track of the selected item
    var selectedItem by remember { mutableStateOf<String?>(null) }

    // Renamed the list to avoid conflict
    val sportsList = listOf(
        "Crickets",
        "Basketball",
        "Tennis",
        "Football",
        "Swimming",
        "Ping pong"
    )

    DefaultScreenUI(
        onRemoveHeadFromQueue = { }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer_24dp()

                // Step Indicators
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StepItem(
                        icon = painterResource(Res.drawable.one),
                        title = "Sign up",
                        isActive = true,
                        color = Color(0xFF304C75)
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.dp,
                        color = light_gray
                    )
                    StepItem(
                        icon = painterResource(Res.drawable.two),
                        title = "Profile type",
                        color = page_text_color2,
                        isActive = false
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.dp,
                        color = light_gray
                    )
                    StepItem(
                        icon = painterResource(Res.drawable.three),
                        title = "Registration",
                        color = Color.Gray,
                        isActive = false
                    )
                }

                Spacer_64dp()

                // Greeting and Instructions
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Hello, ${userName.value} \uD83D\uDC4B",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 30.sp,
                            fontFamily = LatoTypography().bodyMedium.fontFamily,
                            fontWeight = FontWeight(600),
                            color = Thick_black,
                            fontStyle = LatoTypography().bodyMedium.fontStyle
                        )
                    )
                    Spacer_8dp()
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Select your sport type",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = LatoTypography().bodySmall.fontFamily,
                            fontWeight = FontWeight(400),
                            color = gray_50,
                            fontStyle = LatoTypography().bodyMedium.fontStyle
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer_64dp()

                    // Updated LazyColumn for single selection
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(sportsList.size) { sport ->
                            SingleSelectionListItem(
                                text = sportsList[sport],
                                isSelected = sportsList[sport] == selectedItem,
                                onClick = {
                                    selectedItem = sportsList[sport] // Update selected item
                                    onItemSelected(sportsList[sport]) // Notify parent about selection
                                }
                            )
                        }
                    }
                }
            }

            // Bottom "Next" button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                NormalRoundedButton(
                    textButton = "Next",
                    textColor = Color.White,
                    containerColor = PrimaryColor,
                    enabled = selectedItem != null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    selectedItem?.let {
                        // Perform the next step
                        onNextClicked.invoke()
                    }
                }
            }
        }
    }
}