package com.golvia.ng.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.InputFieldHeader
import com.golvia.ng.presentation.component.NormalRoundedButton
import com.golvia.ng.presentation.component.OutlinedInputField
import com.golvia.ng.presentation.component.Spacer_16dp
import com.golvia.ng.presentation.component.Spacer_32dp
import com.golvia.ng.presentation.component.Spacer_4dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.Thick_black
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.arrow_left
import golvia.shared.generated.resources.arrow_right
import golvia.shared.generated.resources.back
import golvia.shared.generated.resources.email_address
import golvia.shared.generated.resources.enter_your_email
import golvia.shared.generated.resources.forget_password
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun ForgotPasswordScreen(
    popUp: () -> Unit
){
    DefaultScreenUI(
        backIconToolbar = Icons.AutoMirrored.Default.ArrowBack,
        titleText = stringResource(Res.string.back),
        onClickStartIconToolbar = { popUp() },
        onRemoveHeadFromQueue = { },
    ) {
        val email by remember { mutableStateOf("") }

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
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(Res.string.enter_your_email),
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 30.sp,
                        fontFamily = LatoTypography().bodyMedium.fontFamily,
                        fontWeight = FontWeight(600),
                        color = Thick_black,
                        fontStyle = LatoTypography().bodyMedium.fontStyle
                    )
                )
                Spacer_32dp()

                Column(horizontalAlignment = Alignment.Start) {
                    InputFieldHeader(
                        textValue = stringResource(Res.string.email_address)
                    )
                    Spacer_4dp()
                    OutlinedInputField(
                        textFieldValue = mutableStateOf(email),
                        keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email,
                    )
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
                    textButton = stringResource(Res.string.forget_password),
                    textColor = Color.White,
                    containerColor = PrimaryColor,
                    enabled = true,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    // Todo call api to send otp
                }
            }

        }
    }
}