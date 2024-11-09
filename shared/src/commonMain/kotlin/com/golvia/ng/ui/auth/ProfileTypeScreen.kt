package com.golvia.ng.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.golvia.ng.businessLayer.domain.OptionItem
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.NormalRoundedButton
import com.golvia.ng.presentation.component.OptionItemView
import com.golvia.ng.presentation.component.Spacer_16dp
import com.golvia.ng.presentation.component.Spacer_24dp
import com.golvia.ng.presentation.component.Spacer_32dp
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
import golvia.shared.generated.resources.ic_athletes
import golvia.shared.generated.resources.ic_club_team
import golvia.shared.generated.resources.ic_fan_base
import golvia.shared.generated.resources.ic_scout
import golvia.shared.generated.resources.one
import golvia.shared.generated.resources.three
import golvia.shared.generated.resources.two
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

/**
 * davidsunday
 */

@Composable
fun ProfileTypeScreen(
    popUp: () -> Unit,
    onOptionSelected: (String) -> Unit = {}
) {
    val authViewModel: AuthViewModel = koinInject()
    val userName = authViewModel.userName.collectAsState()

    // State for the currently selected option
    var selectedOption by remember { mutableStateOf<String?>(null) }

    // Define options
    val options = listOf(
        OptionItem("Athletes", painterResource(Res.drawable.ic_athletes)),
        OptionItem("Scout", painterResource(Res.drawable.ic_scout)),
        OptionItem("Club/Team", painterResource(Res.drawable.ic_club_team)),
        OptionItem("Fan base", painterResource(Res.drawable.ic_fan_base))
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
                        isActive = true
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

                Spacer_32dp()

                // Greeting and Instructions
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
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
                        text = "Select your profile type",
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

                    // Options Grid
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            OptionItemView(
                                option = options[0],
                                isSelected = options[0].label == selectedOption
                            ) {
                                selectedOption = options[0].label
                                onOptionSelected(options[0].label)
                            }
                            OptionItemView(
                                option = options[1],
                                isSelected = options[1].label == selectedOption
                            ) {
                                selectedOption = options[1].label
                                onOptionSelected(options[1].label)
                            }
                        }
                        Spacer_16dp()
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            OptionItemView(
                                option = options[2],
                                isSelected = options[2].label == selectedOption
                            ) {
                                selectedOption = options[2].label
                                onOptionSelected(options[2].label)
                            }
                            OptionItemView(
                                option = options[3],
                                isSelected = options[3].label == selectedOption
                            ) {
                                selectedOption = options[3].label
                                onOptionSelected(options[3].label)
                            }
                        }
                    }
                }
            }

            // Bottom Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                NormalRoundedButton(
                    textButton = "Get Started",
                    textColor = Color.White,
                    containerColor = PrimaryColor,
                    enabled = selectedOption != null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    // Navigate or perform action based on selection
                    selectedOption?.let {
                        popUp()
                    }
                }
            }
        }
    }
}