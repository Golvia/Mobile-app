package com.golvia.ng.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.common.ChangeStatusBarColors
import com.golvia.ng.presentation.component.CustomPinCodeField
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.NormalRoundedButton
import com.golvia.ng.presentation.component.Spacer_64dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.component.TextCorrect
import com.golvia.ng.presentation.component.TextFieldState
import com.golvia.ng.presentation.component.TextIncorrect
import com.golvia.ng.presentation.component.TextMissing
import com.golvia.ng.presentation.component.TextState
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.Thick_black
import com.golvia.ng.presentation.theme.gray_10
import com.golvia.ng.presentation.theme.gray_100
import com.golvia.ng.presentation.theme.gray_45
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.ui.auth.viewModel.AuthViewModel
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.back
import golvia.shared.generated.resources.ic_logo_header
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

/**
 * davidsunday
 */

@Composable
fun OTPScreen(
    popBackStack: () -> Unit,
){

    val viewModel: AuthViewModel = koinInject()
    val focusManager = LocalFocusManager.current
    val codeStates = remember { List(6) { TextFieldState(String()) } }
    val remainingTime = viewModel.remainingTime.collectAsState()
    val isCountdownRunning = viewModel.isCountdownRunning.collectAsState()

    val authenticatorCode = codeStates.joinToString("") { state -> state.value }

    val pinErrorStateCurrent = remember { mutableStateOf<TextState?>(null) }

    LaunchedEffect(viewModel.shouldStartCountdown){
        if(viewModel.shouldStartCountdown.value) viewModel.startCountdown(60000)
    }

    ChangeStatusBarColors(Color.White)
    DefaultScreenUI(
        backIconToolbar = Icons.AutoMirrored.Default.ArrowBack,
        titleText = stringResource(Res.string.back),
        onClickStartIconToolbar = { popBackStack() },
        onRemoveHeadFromQueue = { },
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(120.dp),
                    painter = painterResource(Res.drawable.ic_logo_header),
                    contentDescription = null,
                )

                Spacer_8dp()
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Hello, James",
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
                    text = "We sent you a verification code to",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = LatoTypography().bodySmall.fontFamily,
                        fontWeight = FontWeight(600),
                        color = gray_50,
                        fontStyle = LatoTypography().bodyMedium.fontStyle
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "jam******@***.com",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = LatoTypography().bodySmall.fontFamily,
                        fontWeight = FontWeight(600),
                        color = gray_100,
                        fontStyle = LatoTypography().bodyMedium.fontStyle
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer_64dp()

                CustomPinCodeField(
                    label = "",
                    nFields = 6,
                    pinStates = codeStates,
                    focusManager = focusManager,
                    textState = when (pinErrorStateCurrent.value) {
                        is TextMissing -> TextMissing("Invalid OTP")
                        is TextIncorrect -> TextIncorrect("OTP is Incorrect")
                        else -> TextCorrect()
                    },
                    checker = { },
                    clearPinError = { TextCorrect() }
                )
                Spacer_8dp()
                if (isCountdownRunning.value) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "OTP expires in " + remainingTime.value,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontFamily = LatoTypography().bodySmall.fontFamily,
                            fontWeight = FontWeight(600),
                            color = gray_50,
                            fontStyle = LatoTypography().bodySmall.fontStyle
                        ),
                        textAlign = TextAlign.Center
                    )
                }else{
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                viewModel.startCountdown(60000)
                            },
                        text = "Send OTP again",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 28.sp,
                            fontFamily = LatoTypography().bodySmall.fontFamily,
                            fontWeight = FontWeight(600),
                            color = Color.Black,
                            fontStyle = LatoTypography().bodySmall.fontStyle
                        ),
                        textAlign = TextAlign.Center
                    )
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
                    enabled = authenticatorCode.isNotEmpty() && authenticatorCode.length == codeStates.size,
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