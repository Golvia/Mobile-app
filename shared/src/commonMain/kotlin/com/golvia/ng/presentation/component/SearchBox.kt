package com.golvia.ng.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.TextFieldWithTransparentTheme
import com.golvia.ng.presentation.theme.gray_50
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.search
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@Composable
fun SearchBox(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchExecute: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val cornerRadius = RoundedCornerShape(percent = 50)

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(0.6f)
            .fillMaxHeight(0.85f)
            .border(
                width = 1.dp,
                color = LightGray,
                shape = cornerRadius
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer_8dp()
        Icon(
            painter = painterResource(Res.drawable.search),
            contentDescription = null,
            tint = Black,
            modifier = Modifier
                .size(20.dp)
                .noRippleClickable {
                    onSearchExecute()
                    keyboardController?.hide()
                }
        )

        Box(
            modifier = Modifier
                .background(Color.White, cornerRadius)
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = "Search",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(300),
                            fontFamily = LatoTypography().bodySmall.fontFamily
                        ),
                        color = gray_50
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchExecute()
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldWithTransparentTheme()
            )
        }

    }
}