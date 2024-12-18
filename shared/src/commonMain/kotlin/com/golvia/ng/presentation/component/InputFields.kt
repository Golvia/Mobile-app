package com.golvia.ng.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.BorderColor
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.TextFieldWithTransparentTheme
import com.golvia.ng.presentation.theme.gray_10
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.presentation.theme.hint_color
import com.golvia.ng.presentation.theme.red_error
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.arrow_down
import golvia.shared.generated.resources.email_hint
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
 fun OutlinedInputField(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    isError: Boolean = false,
    errorValue: String? = null,
    enabled: Boolean = true,
    showDropDown: Boolean = false,
    placeholder: String? = stringResource(Res.string.email_hint),
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email,
    )
) {
    val textColor = if (textFieldValue.isNotEmpty()) Color.Black else gray_10

     Column {
         Box(
             modifier = modifier
                 .fillMaxWidth()
                 .background(Color.White)
                 .border(1.dp, if (isError) red_error else  LightGray, MaterialTheme.shapes.small)
         ) {
             Row(
                 modifier = Modifier
                     .fillMaxWidth(),
                 horizontalArrangement = Arrangement.Start,
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 TextField(
                     enabled = enabled,
                     value = textFieldValue,
                     placeholder = {
                         Text(placeholder.orEmpty(), color = gray_10)
                     },
                     onValueChange = {
                         if (it.length < 32) {
                             onValueChange(it)
                         }
                     },
                     modifier = Modifier
                         .fillMaxWidth()
                         .weight(0.9f)
                         .background(Color.Transparent),
                     colors = TextFieldWithTransparentTheme(),
                     shape = MaterialTheme.shapes.small,
                     singleLine = true,
                     keyboardOptions = keyboardOptions,
                 )

                 if(showDropDown){
                     Image(
                         painter = painterResource(Res.drawable.arrow_down),
                         contentDescription = null,
                         modifier = Modifier.padding(end = 12.dp)
                     )
                 }
             }
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

@Composable
fun CustomRegularOutlinedTextFieldPin(
    fieldState: TextFieldState,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textAlign: TextAlign = TextAlign.Start,
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit
) {
    BasicTextField(
        value = fieldState.value,
        modifier = modifier.size(48.dp),
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = TextStyle(
            textAlign = textAlign,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = LatoTypography().bodyMedium.fontFamily,
            fontWeight = FontWeight(400),
            color = Black
        ),
        singleLine = true,
        decorationBox = decorationBox
    )
}

@Composable
fun CustomRegularDecorationBox(
    value: String,
    placeholder: String = String(),
    errorState: Boolean = false,
    hasMultilinePlaceholder: Boolean = false,
    innerTextField: @Composable () -> Unit,
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 1.dp,
            color = if (errorState) red_error else BorderColor,
            shape = RoundedCornerShape(size = 10.dp)
        )
        .padding(horizontal = 16.dp, vertical = 16.dp),
) {
    if (value.isEmpty()) {
        if (hasMultilinePlaceholder) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                text = placeholder,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = LatoTypography().bodyMedium.fontFamily,
                    fontWeight = FontWeight(400),
                    color = Black,
                ),
            )
        } else {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                text = placeholder,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = LatoTypography().bodyMedium.fontFamily,
                    fontWeight = FontWeight(400),
                    color = Black,
                ),
            )
        }
    }
    innerTextField()
}