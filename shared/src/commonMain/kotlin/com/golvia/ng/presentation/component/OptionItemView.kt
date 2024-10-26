package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.golvia.ng.businessLayer.domain.OptionItem
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.close
import golvia.shared.generated.resources.delete
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@Composable
fun OptionItemView(option: OptionItem, onClick: () -> Unit) {
    val borderColor = if (option.isSelected) Color(0xFF00C853) else Color.LightGray
    val iconBackground = if (option.isSelected) Color(0xFFD7F9E3) else Color.LightGray

    Column(
        modifier = Modifier
            .size(100.dp)
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(iconBackground, CircleShape)
        ) {
            Icon(
                painter = painterResource(Res.drawable.close),
                contentDescription = option.label,
                tint = Color.Black
            )
            if (option.isSelected) {
                Icon(
                    painter = painterResource(Res.drawable.close),
                    contentDescription = null,
                    tint = Color(0xFF00C853),
                    modifier = Modifier.align(Alignment.TopEnd).size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = option.label, style = MaterialTheme.typography.bodySmall)
    }
}