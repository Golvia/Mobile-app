package com.golvia.ng.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.common.ChangeStatusBarColors
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.DefaultText
import com.golvia.ng.presentation.component.DividerWithTextContent
import com.golvia.ng.presentation.component.InputFieldHeader
import com.golvia.ng.presentation.component.NormalRoundedButton
import com.golvia.ng.presentation.component.OutlinedInputField
import com.golvia.ng.presentation.component.OutlinedRoundedButtonWithIcon
import com.golvia.ng.presentation.component.PasswordTextField
import com.golvia.ng.presentation.component.Spacer_16dp
import com.golvia.ng.presentation.component.Spacer_24dp
import com.golvia.ng.presentation.component.Spacer_32dp
import com.golvia.ng.presentation.component.Spacer_4dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.Thick_black
import com.golvia.ng.presentation.theme.default_black
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.presentation.theme.light_gray
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.continue_with_google
import golvia.shared.generated.resources.email_address
import golvia.shared.generated.resources.forget_password
import golvia.shared.generated.resources.forgot_account
import golvia.shared.generated.resources.ic_google_icon
import golvia.shared.generated.resources.ic_logo_header
import golvia.shared.generated.resources.login
import golvia.shared.generated.resources.login_message
import golvia.shared.generated.resources.login_with_email
import golvia.shared.generated.resources.password
import golvia.shared.generated.resources.sign_up
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToForgotPassword: () -> Unit
) {
    ChangeStatusBarColors(Color.White)

    DefaultScreenUI(
        onRemoveHeadFromQueue = { },
    ) {
        val email = remember { mutableStateOf(String()) }
        val password = remember { mutableStateOf(String()) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                    text = stringResource(Res.string.login_message),
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 30.sp,
                        fontFamily = LatoTypography().bodyMedium.fontFamily,
                        fontWeight = FontWeight(600),
                        color = Thick_black,
                        fontStyle = LatoTypography().bodyMedium.fontStyle
                    )
                )
               Spacer_24dp()
                OutlinedRoundedButtonWithIcon(
                    textButton = stringResource(Res.string.continue_with_google),
                    textColor = Color.Black,
                    borderColor = light_gray,
                    containerColor = Color.White,
                    enabled = true,
                    painter = painterResource(Res.drawable.ic_google_icon),
                    onClick = {
                        //Todo add google login
                    }
                )
               Spacer_32dp()
                DividerWithTextContent(
                    textResource = stringResource(Res.string.login_with_email),
                    textColor = gray_50,
                    dividerColor = light_gray
                )
                Spacer_16dp()

                Column(horizontalAlignment = Alignment.Start) {
                    InputFieldHeader(
                        textValue = stringResource(Res.string.email_address)
                    )
                    Spacer_4dp()
                    OutlinedInputField(
                        textFieldValue = email.value,
                        onValueChange = {
                            email.value = it
                        }
                    )
                    Spacer_24dp()
                    InputFieldHeader(
                        textValue = stringResource(Res.string.password)
                    )
                    PasswordTextField(
                        // isError = isPasswordError,
                        value = password.value,
                        onValueChange = {
                            password.value = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Spacer_8dp()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    DefaultText(
                        modifier = Modifier
                            .clickable {
                                navigateToForgotPassword()
                            },
                        textValue = stringResource(Res.string.forget_password),
                        color = Color.Black,
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontFamily = LatoTypography().titleMedium.fontFamily
                        ),
                        textDecoration = TextDecoration.Underline
                    )
                }

                Spacer(modifier = Modifier.height(300.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                NormalRoundedButton(
                    textButton = stringResource(Res.string.login),
                    textColor = Color.White,
                    containerColor = PrimaryColor,
                    enabled = email.value.isNotEmpty() && password.value.isNotEmpty(),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    // Todo navigate to main
                    navigateToMain.invoke()
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    DefaultText(
                        textValue = stringResource(Res.string.forgot_account),
                        color = default_black)
                    DefaultText(
                        modifier = Modifier
                            .clickable {
                                navigateToRegister()
                            },
                        textValue = stringResource(Res.string.sign_up),
                        color = PrimaryColor,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight(500))
                }
            }

        }
    }
}

