package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.businessLayer.domain.OptionItem
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.presentation.theme.grey_050
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.ic_selected_icon
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@Composable
fun OptionItemView(
    option: OptionItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFF2FC66B) else Color.LightGray
    val iconBackground = if (isSelected) Color(0xFFD7F9E3) else Color.LightGray

    Column(
        modifier = Modifier
            .size(128.dp)
            .border(1.4.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isSelected) {
            Icon(
                modifier = Modifier.align(Alignment.End).padding(8.dp),
                painter = painterResource(Res.drawable.ic_selected_icon),
                contentDescription = option.label,
                tint = Color.Unspecified
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(grey_050, CircleShape)
        ) {
            Icon(
                painter = option.iconRes,
                contentDescription = option.label,
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = option.label,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontFamily = LatoTypography().bodyMedium.fontFamily,
                fontWeight = FontWeight(400),
                color = Color.Black,
                fontStyle = LatoTypography().bodyMedium.fontStyle
            ),
            textAlign = TextAlign.Center
        )
    }
}

//