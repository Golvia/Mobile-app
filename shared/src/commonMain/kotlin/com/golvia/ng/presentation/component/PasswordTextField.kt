package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.TextFieldWithTransparentTheme
import com.golvia.ng.presentation.theme.gray_10
import com.golvia.ng.presentation.theme.gray_20
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.presentation.theme.icon_default_color
import com.golvia.ng.presentation.theme.red_error
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.ic_password_hide
import golvia.shared.generated.resources.ic_password_show
import golvia.shared.generated.resources.password_hint
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    readOnly: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    placeholder: String? = stringResource(Res.string.password_hint),
    errorValue: String? = null,
    onValueChange: (String) -> Unit,
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = enabled) {
        if (!enabled) isPasswordVisible.value = false
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp,if (isError) red_error else  LightGray, MaterialTheme.shapes.small)
        ){
            TextField(
                modifier = modifier,
                placeholder = {
                    Text(placeholder.orEmpty(), color = gray_10)
                },
                colors = TextFieldWithTransparentTheme(),
                shape = MaterialTheme.shapes.small,
                readOnly = readOnly,
                value = value,
                onValueChange = {
                    onValueChange(it)
                                },
                trailingIcon = {
                    IconButton(onClick = {
                        if (enabled) {
                            isPasswordVisible.value = !isPasswordVisible.value
                        }
                    }) {
                        when (isPasswordVisible.value) {
                            true -> Icon(
                                painter = painterResource(Res.drawable.ic_password_hide),
                                contentDescription = null,
                                tint = gray_50,
                            )

                            false -> Icon(
                                painter = painterResource(Res.drawable.ic_password_show),
                                contentDescription = null,
                                tint = gray_50,
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                ),
                visualTransformation = when (isPasswordVisible.value) {
                    true -> VisualTransformation.None
                    false -> PasswordVisualTransformation()
                },
            )

        }

        Spacer(modifier = Modifier.padding(top = 2.dp))
        if (isError){
            Text(
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontFamily = LatoTypography().bodySmall.fontFamily
                ),
                text = errorValue.orEmpty(),
                color = red_error
            )
        }
    }
}
