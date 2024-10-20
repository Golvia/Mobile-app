package com.golvia.ng.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.business.core.ProgressBarState
import com.golvia.ng.presentation.theme.BorderColor
import com.golvia.ng.presentation.theme.DefaultButtonTheme
import com.golvia.ng.presentation.theme.DefaultButtonWithBorderPrimaryTheme
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.gray_50

val DEFAULT__BUTTON_SIZE = 50.dp
val DEFAULT__BUTTON_SIZE_EXTRA = 60.dp


@Composable
fun CircleButton(
    modifier : Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.size(50.dp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, BorderColor),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector, null)
        }
    }
}

@Composable
fun ButtonLoading(
    modifier: Modifier = Modifier,
    progressBarState: ProgressBarState,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        enabled = (enabled || progressBarState != ProgressBarState.Idle),
        modifier = modifier,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            AnimatedVisibility(visible = (progressBarState == ProgressBarState.ButtonLoading || progressBarState == ProgressBarState.FullScreenLoading)) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(25.dp),
                    strokeWidth = 2.dp,
                    color = if (enabled) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
                )
            }

            content()
        }
    }
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    enabled: Boolean = true,
    enableElevation: Boolean = false,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    text: String,
    onClick: () -> Unit,
) {
    ButtonLoading(
        enabled = enabled,
        modifier = modifier,
        elevation = if (enableElevation) ButtonDefaults.buttonElevation() else ButtonDefaults.buttonElevation(
            0.dp
        ),
        colors = if (enabled) DefaultButtonTheme() else DefaultButtonWithBorderPrimaryTheme(),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ),
        shape = shape,
        onClick = onClick,
        progressBarState = progressBarState,
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}

@Composable
fun OutlinedRoundedButtonWithIcon(
    modifier: Modifier = Modifier,
    textButton: String,
    textColor: Color,
    borderColor: Color,
    containerColor: Color,
    enabled: Boolean,
    painter: Painter,
    painterDisabled: Painter = painter,
    textColorDisabled: Color = LightGray,
    borderColorDisabled: Color = LightGray,
    containerColorDisabled: Color = White,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        elevation = null,
        border = BorderStroke(1.dp, if (enabled) borderColor else borderColorDisabled),
        colors = ButtonDefaults.outlinedButtonColors(
            if (enabled) containerColor else containerColorDisabled
        )
    ) {
       Row(
           modifier = Modifier.padding(8.dp)
       ) {
           Icon(
               if (enabled) painter else painterDisabled,
               contentDescription = "drawable icons",
               tint = Color.Unspecified
           )
           Spacer(modifier = Modifier.width(16.dp))
           Text(
               modifier = Modifier
                   .padding(vertical = 6.dp),
               text = textButton,
               style = TextStyle(
                   fontSize = 16.sp,
                   lineHeight = 20.sp,
                   fontFamily = LatoTypography().bodyMedium.fontFamily,
                   fontWeight = FontWeight(600),
                   color = if (enabled) textColor else textColorDisabled,
                   textAlign = TextAlign.Center,
               )
           )
       }
    }
}

@Composable
fun NormalRoundedButton(
    textButton: String,
    textColor: Color,
    containerColor: Color,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight(500),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = LightGray,
        )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = textButton,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = LatoTypography().bodyMedium.fontFamily,
                fontWeight = fontWeight,
                color = if (enabled) textColor else Color.Gray,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun OutlinedRoundedButtonWithNoIcon(
    modifier: Modifier = Modifier,
    textButton: String,
    textColor: Color,
    borderColor: Color,
    containerColor: Color,
    enabled: Boolean,
    textColorDisabled: Color = LightGray,
    borderColorDisabled: Color = LightGray,
    containerColorDisabled: Color = White,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.6f),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        elevation = null,
        border = BorderStroke(1.dp, if (enabled) borderColor else borderColorDisabled),
        colors = ButtonDefaults.outlinedButtonColors(
            if (enabled) containerColor else containerColorDisabled
        ),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 4.dp)
    ) {
        Text(
            text = textButton,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontFamily = LatoTypography().bodyLarge.fontFamily,
                fontWeight = FontWeight(700),
                color = if (enabled) textColor else textColorDisabled,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
