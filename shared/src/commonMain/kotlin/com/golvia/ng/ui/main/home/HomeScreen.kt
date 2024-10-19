package com.golvia.ng.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.golvia.ng.presentation.component.DefaultScreenUI

/**
 * davidsunday
 */

@Composable
fun HomeScreen(
    navigateToComment: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
){

    DefaultScreenUI(
        onRemoveHeadFromQueue = {  },
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp), // Bottom-only shape
                        clip = false
                    )
                    .background(Color.White)
            ) {

            }
        }

    }

}
