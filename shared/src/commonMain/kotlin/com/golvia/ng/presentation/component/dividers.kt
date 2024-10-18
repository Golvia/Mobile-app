package com.golvia.ng.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun DividerWithTextContent( textResource: String, textColor: Color, dividerColor: Color) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .width(0.dp)
                .weight(1f),
            thickness = 1.dp, color = dividerColor
        )
        Text(
            text = textResource,
            color = textColor,
            fontFamily = LatoTypography().bodyMedium.fontFamily,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
        HorizontalDivider(
            modifier = Modifier
                .width(0.dp)
                .weight(1f),
            thickness = 1.dp, color = dividerColor
        )
    }
}