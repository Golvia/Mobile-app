package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.card_background
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.presentation.theme.medium_text_color
import com.golvia.ng.presentation.theme.progress_color

/**
 * davidsunday
 */

@Composable
fun ProfileCompletionCard(
    progress: Float,
    description: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {

    val cornerRadius = RoundedCornerShape(8.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(cornerRadius),
        colors = CardColors(
            containerColor = card_background,
            contentColor = Color.Black,
            disabledContainerColor = card_background,
            disabledContentColor = Color.Black
        ),
        shape = cornerRadius
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(48.dp)
                ) {

                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.size(48.dp),
                        color = progress_color,
                        strokeWidth = 4.dp,
                        trackColor = Color.White
                    )

                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        fontFamily = LatoTypography().bodyMedium.fontFamily
                    )
                }

                Spacer_16dp()

                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = gray_50
                    ),
                    fontFamily = LatoTypography().bodyMedium.fontFamily
                )
            }

            Button(
                onClick = onButtonClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier.height(40.dp)
            ) {
                Text(
                    text = buttonText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}