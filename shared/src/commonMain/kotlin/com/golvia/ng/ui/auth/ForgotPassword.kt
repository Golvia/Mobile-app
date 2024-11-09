package com.golvia.ng.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.DefaultText
import com.golvia.ng.presentation.component.InputFieldHeader
import com.golvia.ng.presentation.component.NormalRoundedButton
import com.golvia.ng.presentation.component.OutlinedInputField
import com.golvia.ng.presentation.component.Spacer_16dp
import com.golvia.ng.presentation.component.Spacer_24dp
import com.golvia.ng.presentation.component.Spacer_32dp
import com.golvia.ng.presentation.component.Spacer_4dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.Thick_black
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.ui.auth.viewModel.AuthViewModel
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.arrow_left
import golvia.shared.generated.resources.arrow_right
import golvia.shared.generated.resources.back
import golvia.shared.generated.resources.email_address
import golvia.shared.generated.resources.enter_your_email
import golvia.shared.generated.resources.forget_password
import golvia.shared.generated.resources.ic_logo_header
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

/**
 * davidsunday
 */

@Composable
fun ForgotPasswordScreen(
    popUp: () -> Unit,
    popUpToLogin: () -> Unit,
    popUpToForgotPasswordLink: () -> Unit
) {
    val authViewModel: AuthViewModel = koinInject()
    val email = remember { mutableStateOf("") }

    DefaultScreenUI(
        backIconToolbar = Icons.AutoMirrored.Default.ArrowBack,
        titleText = stringResource(Res.string.back),
        onClickStartIconToolbar = { popUp() },
        onRemoveHeadFromQueue = { }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F3F9))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // App logo
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(Res.drawable.ic_logo_header),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Content Box with rounded corners and centered content
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp, bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Title text
                        Text(
                            text = "Forgot Password?",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight(600),
                                color = Thick_black,
                                fontFamily = LatoTypography().bodyMedium.fontFamily
                            ),
                            textAlign = TextAlign.Center
                        )
                        Spacer_8dp()

                        Text(
                            text = "Fill in your email and we will send\n" +
                                    "you a link to reset your password.",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 22.sp,
                                fontWeight = FontWeight(400),
                                color = gray_50,
                                fontFamily = LatoTypography().bodySmall.fontFamily
                            ),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Input field for email
                        Column(horizontalAlignment = Alignment.Start) {
                            InputFieldHeader(
                                textValue = "Email"
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedInputField(
                                textFieldValue = email.value,
                                onValueChange = { email.value = it },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Email
                                ),
                                placeholder = "Enter email"
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        // Action button
                        NormalRoundedButton(
                            textButton = "Continue",
                            textColor = Color.White,
                            containerColor = PrimaryColor,
                            enabled = email.value.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            authViewModel.email.value = email.value
                            popUpToForgotPasswordLink.invoke()
                        }

                        Spacer_8dp()

                        DefaultText(
                            modifier = Modifier
                                .clickable {
                                    popUpToLogin.invoke()
                                },
                            textValue = "Back to Login",
                            color = PrimaryColor,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = LatoTypography().titleMedium.fontFamily
                            ),
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
        }
    }
}