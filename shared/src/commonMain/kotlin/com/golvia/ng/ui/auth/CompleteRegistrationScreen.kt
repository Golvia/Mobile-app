package com.golvia.ng.ui.auth

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.LinearProgressWithDeterminateValue
import com.golvia.ng.presentation.component.Spacer_24dp
import com.golvia.ng.presentation.component.Spacer_32dp
import com.golvia.ng.ui.auth.viewModel.AuthViewModel
import org.koin.compose.koinInject

/**
 * davidsunday
 */

@Composable
fun CompleteRegistrationScreen(
    popUp: () -> Unit,
    navigateToHome: () -> Unit
){
    val authViewModel: AuthViewModel = koinInject()

    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 3000)
        )
        navigateToHome.invoke()
    }

    DefaultScreenUI(
        onRemoveHeadFromQueue = { }
    ){
        Spacer_24dp()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F6FA)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Success message
                Text(
                    text = "Your account has\nbeen created successfully",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer_32dp()

                // Progress bar
                LinearProgressWithDeterminateValue(
                    progress = progress.value,
                    color = Color(0xFF4CAF50),
                    trackColor = Color(0xFFECEFF1)
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Subtitle
                Text(
                    text = "Setting up your profile page",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}