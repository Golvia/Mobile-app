package com.golvia.ng.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.golvia.ng.businessLayer.domain.createDummyFeedsData
import com.golvia.ng.presentation.component.CircularImageView
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.OutlinedRoundedButtonWithNoIcon
import com.golvia.ng.presentation.component.PostContent
import com.golvia.ng.presentation.component.ProfileCompletionCard
import com.golvia.ng.presentation.component.RoundedFlatButton
import com.golvia.ng.presentation.component.SearchBox
import com.golvia.ng.presentation.component.Spacer_12dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.theme.faint_red
import com.golvia.ng.presentation.theme.gold_color
import com.golvia.ng.presentation.theme.light_gold
import com.golvia.ng.presentation.theme.screen_colour
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.go_premium
import golvia.shared.generated.resources.ic_post_icon
import golvia.shared.generated.resources.ic_video_icon
import golvia.shared.generated.resources.sample_profile_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun HomeScreen(
    navigateToComment: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
){

    val searchValue = remember { mutableStateOf("") }

    DefaultScreenUI(
        onRemoveHeadFromQueue = { },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(screen_colour),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp),
                        clip = false
                    )
                    .background(Color.White)
            ) {

                Row(
                    modifier = Modifier
                        .padding(top = 42.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchBox(
                        value = searchValue.value,
                        onValueChange = { searchValue.value = it },
                        onSearchExecute = { },
                        modifier = Modifier.weight(0.7f)
                    )

                    Spacer_12dp()

                    OutlinedRoundedButtonWithNoIcon(
                        textButton = stringResource(Res.string.go_premium),
                        textColor = gold_color,
                        borderColor = gold_color,
                        containerColor = light_gold,
                        enabled = true,
                        modifier = Modifier.weight(0.25f)
                    ) {
                        // Todo navigate to premium screen
                    }

                    Spacer_12dp()

                    CircularImageView(
                        image = painterResource(Res.drawable.sample_profile_image),
                        borderColor = LightGray,
                        borderWidth = 0.5.dp,
                        size = 40.dp,
                        modifier = Modifier.weight(0.12f)
                    )
                }
            }

           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(start = 16.dp, end = 16.dp)
                  // .verticalScroll(rememberScrollState())
                   .background(screen_colour),
               verticalArrangement = Arrangement.Top,
               horizontalAlignment = Alignment.Start
           ){
               Spacer_12dp()
               ProfileCompletionCard(
                   progress = 0.4f,
                   description = "Complete your profile to\n" +
                           "start using the golvia app.",
                   buttonText = "Complete"
               ){
                   // Todo navigate to profile screen
               }

               Spacer(modifier = Modifier.padding(top = 12.dp))
               Row(
                   modifier = Modifier
                       .fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween,
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   RoundedFlatButton(
                       modifier = Modifier.weight(0.6f),
                       text = "Start a post",
                       icon = painterResource(Res.drawable.ic_post_icon),
                       onClick = {
                           // Todo navigate to post screen
                       }
                   )

                   RoundedFlatButton(
                       modifier = Modifier.padding(start = 4.dp).weight(0.4f),
                       text = "Go live",
                       tintColor = faint_red,
                       icon = painterResource(Res.drawable.ic_video_icon),
                       onClick = {
                           // Todo navigate to post screen
                       }
                   )
               }
               Spacer_8dp()
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .clickable {

                    },
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                items(createDummyFeedsData().size, key = { it }) {
                    PostContent(
                        modifier = Modifier.fillMaxSize().padding(top = 8.dp),
                        feedsData = createDummyFeedsData()[it],
                        onLikeClick = {},
                        onCommentClick = {},
                        onShareClick = {}
                    )
                }

            }
           }
        }
    }

}
