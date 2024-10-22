package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.TextFieldWithTransparentTheme
import com.golvia.ng.presentation.theme.red_error
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.email_hint
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
 fun OutlinedInputField(
    textFieldValue: String,
    isError: Boolean = false,
    errorValue: String? = null,
    placeholder: String? = stringResource(Res.string.email_hint),
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email,
    )
) {
     Column {
         Box(
             modifier = Modifier
                 .fillMaxWidth()
                 .background(Color.White)
                 .border(1.dp, if (isError) red_error else  LightGray, MaterialTheme.shapes.small)
         ) {
             TextField(
                 value = textFieldValue,
                 placeholder = {
                     Text(placeholder.orEmpty(), color = Color.Gray)
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