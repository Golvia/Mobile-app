package com.golvia.ng.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import golvia.shared.generated.resources.close
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
    selectedOption: String = String(),
    onOptionSelected: (String) -> Unit = {}
) {

    val authViewModel: AuthViewModel = koinInject()
    val userName = authViewModel.userName.collectAsState()

    val options = listOf(
        OptionItem("Athletes", painterResource(Res.drawable.close), isSelected = selectedOption == "Athletes"),
        OptionItem("Scout", painterResource(Res.drawable.close), isSelected = selectedOption == "Scout"),
        OptionItem("Club/Team", painterResource(Res.drawable.close), isSelected = selectedOption == "Club/Team"),
        OptionItem("Fan base", painterResource(Res.drawable.close), isSelected = selectedOption == "Fan base")
    )


    DefaultScreenUI(
        onRemoveHeadFromQueue = { }
    ) {
       Box(
           modifier = Modifier
               .fillMaxSize()
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

               Spacer_32dp()
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

                   Spacer_16dp()
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       horizontalAlignment = Alignment.CenterHorizontally) {
                       Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                           OptionItemView(options[0]) { onOptionSelected("Athletes") }
                           OptionItemView(options[1]) { onOptionSelected("Scout") }
                       }
                       Spacer_16dp()
                       Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                           OptionItemView(options[2]) { onOptionSelected("Club/Team") }
                           OptionItemView(options[3]) { onOptionSelected("Fan base") }
                       }
                   }
               }
           }

           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .align(Alignment.BottomCenter)
           ) {
               NormalRoundedButton(
                   textButton = "Get Started",
                   textColor = Color.White,
                   containerColor = PrimaryColor,
                   enabled = true,
                   modifier = Modifier
                       .align(Alignment.CenterHorizontally)
                       .padding(16.dp)
               ) {
                   // Todo navigate to main
               }
           }
       }
    }
}