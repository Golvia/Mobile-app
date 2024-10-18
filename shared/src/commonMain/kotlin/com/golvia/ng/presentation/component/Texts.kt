package com.golvia.ng.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.gray_50

/**
 * davidsunday
 */

@Composable
 fun InputFieldHeader(
     textValue: String
 ) {
    Text(
        text = textValue,
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = LatoTypography().bodySmall.fontFamily
        ),
        fontWeight = FontWeight(400),
        color = gray_50,
        fontStyle = LatoTypography().bodySmall.fontStyle
    )
}

@Composable
fun DefaultText(
    modifier: Modifier = Modifier,
    textValue: String,
    color: Color,
    textStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = LatoTypography().bodySmall.fontFamily
    ),
    fontWeight: FontWeight = FontWeight(400),
    fontStyle: FontStyle? = LatoTypography().bodySmall.fontStyle,
    textDecoration: TextDecoration? = null
) {
    Text(
        modifier = modifier,
        text = textValue,
        style = textStyle,
        fontWeight = fontWeight,
        color = color,
        fontStyle = fontStyle,
        textDecoration = textDecoration

    )
}