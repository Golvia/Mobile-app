package com.golvia.ng.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.gray_50

/**
 * davidsunday
 */

@Composable
fun CustomSearchViewField(
    modifier: Modifier,
    searchState: TextFieldState,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = searchState.value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontFamily = LatoTypography().bodyMedium.fontFamily,
            fontWeight = FontWeight(400),
            color = Color.Black
        ),
        singleLine = true,
        decorationBox = decorationBox
    )
}

@Composable
fun CustomSearchViewBox(
    search: UIComponentAction<Painter, () -> Unit>,
    clearState: UIComponentAction<Painter, () -> Unit>,
    backState: UIComponentAction<Painter, () -> Unit>? = null,
    fieldState: TextFieldState,
    placeholder: String = String(),
    searchTextSize: TextUnit = 14.sp,
    isFocused: Boolean = false,
    innerTextField: @Composable () -> Unit,
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 1.dp,
            color = if (isFocused) Color.Black else gray_50,
            shape = RoundedCornerShape(size = 8.dp)
        )
        .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    if (backState != null && backState.isVisible) {
        Image(
            modifier = Modifier.clickable { backState.viewAction() },
            painter = backState.viewComponent,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(width = 4.dp))
    }
    Spacer(modifier = Modifier.width(width = 4.dp))
    Box(
        modifier = Modifier
            .weight(1f)
            .padding(end = 4.dp)
            .align(Alignment.CenterVertically),
    ) {
        if (fieldState.value.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = placeholder,
                style = TextStyle(
                    fontSize = searchTextSize,
                    lineHeight = 18.sp,
                    fontFamily = LatoTypography().bodyMedium.fontFamily,
                    fontWeight = FontWeight(400),
                    color = gray_50,
                )
            )
        }
        innerTextField()
    }
    if (fieldState.value.isNotEmpty() && clearState.isVisible) {
        Image(
            modifier = Modifier.clickable { clearState.viewAction() },
            alignment = Alignment.CenterEnd,
            painter = clearState.viewComponent,
            contentDescription = null
        )
    }
    if (search.isVisible) {
        VerticalDivider(
            modifier = Modifier
                .height(24.dp)
                .padding(horizontal = 14.dp),
            thickness = 0.5.dp,
            color = gray_50
        )
        Image(
            modifier = Modifier.clickable { search.viewAction() },
            alignment = Alignment.CenterStart,
            painter = search.viewComponent,
            contentDescription = null
        )
    }
}