package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.golvia.ng.presentation.theme.TextFieldWithTransparentTheme
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.email_hint
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
 fun OutlinedInputField(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email,
    )
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, LightGray, MaterialTheme.shapes.small)
    ) {
        TextField(
            isError = false,
            value = textFieldValue,
            placeholder = {
                Text(stringResource(Res.string.email_hint), color = Color.Gray)
            },
            onValueChange = {
                if (it.length < 32) {
                    onValueChange(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            colors = TextFieldWithTransparentTheme(),
            shape = MaterialTheme.shapes.small,
            singleLine = true,
            keyboardOptions = keyboardOptions,
        )
    }
}