package com.golvia.ng.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.golvia.ng.common.ChangeStatusBarColors
import com.golvia.ng.presentation.component.MultiStateAnimationCircleFilledCanvas
import com.golvia.ng.presentation.component.Spacer_32dp
import com.golvia.ng.presentation.component.Spacer_64dp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.PrimaryVariantColor
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.ic_logo
import golvia.shared.generated.resources.welcome_message
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */
@Composable
internal fun SplashScreen(
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit
) {

    ChangeStatusBarColors(Color.White)
    LaunchedEffect(true){
        delay(3000)
        navigateToLogin()
    }


    Box(
        modifier = Modifier.fillMaxSize().background(PrimaryVariantColor),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier.fillMaxSize().align(Alignment.BottomCenter),
            contentAlignment = Alignment.BottomCenter
        ) {
            MultiStateAnimationCircleFilledCanvas()
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer_32dp()
            Text(
                stringResource(Res.string.welcome_message),
                style = MaterialTheme.typography.headlineSmall,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                fontStyle = LatoTypography().headlineSmall.fontStyle
            )

            Spacer_64dp()
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp),
                painter = painterResource(Res.drawable.ic_logo),
                contentDescription = null,
            )
        }

    }
}