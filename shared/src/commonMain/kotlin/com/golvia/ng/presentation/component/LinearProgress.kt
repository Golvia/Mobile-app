package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

/**
 * davidsunday
 */

@Composable
fun LinearProgressWithDeterminateValue(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF4CAF50),
    trackColor: Color = Color(0xFFECEFF1),
    strokeCap: StrokeCap = StrokeCap.Round
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(trackColor),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(color)
        )
    }
}