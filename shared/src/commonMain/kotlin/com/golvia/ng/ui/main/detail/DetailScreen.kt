package com.golvia.ng.ui.main.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.golvia.ng.businessLayer.domain.FeedsData
import com.golvia.ng.businessLayer.domain.createDummyFeedsData
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.PostContent
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.screen_colour
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.back
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun DetailScreen(
    popBackStack: () -> Unit,
    feedsData: FeedsData
){

    DefaultScreenUI(
        backIconToolbar = Icons.AutoMirrored.Default.ArrowBack,
        titleText = stringResource(Res.string.back),
        onClickStartIconToolbar = { popBackStack() },
        onRemoveHeadFromQueue = { },
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            PostContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                isFromDetail = true,
                feedsData = feedsData,
                onLikeClick = {},
                onCommentClick = {},
                onShareClick = {}
            )
        }

    }

}