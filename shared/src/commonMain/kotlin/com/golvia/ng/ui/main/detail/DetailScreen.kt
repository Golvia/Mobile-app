package com.golvia.ng.ui.main.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.golvia.ng.businessLayer.domain.FeedsData
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.FeedItem
import com.golvia.ng.presentation.component.PostContent
import com.golvia.ng.presentation.component.Spacer_4dp
import com.golvia.ng.presentation.theme.TextFieldWithTransparentTheme
import com.golvia.ng.presentation.theme.gray_10
import com.golvia.ng.presentation.theme.light_gray
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.back
import golvia.shared.generated.resources.ic_globe
import golvia.shared.generated.resources.ic_like
import golvia.shared.generated.resources.ic_post
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun DetailScreen(
    popBackStack: () -> Unit,
    feedsData: FeedsData
) {
    val comment = remember { mutableStateOf("") }
    DefaultScreenUI(
        backIconToolbar = Icons.AutoMirrored.Default.ArrowBack,
        titleText = stringResource(Res.string.back),
        onClickStartIconToolbar = { popBackStack() },
        onRemoveHeadFromQueue = { },
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
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

                    Spacer_4dp()
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(feedsData.comments.size) { feed ->
                        FeedItem(comment = feedsData.comments.get(feed))
                    }
                }
            }


            // Bottom Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(alignment = Alignment.BottomCenter)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Like Icon
                Icon(
                    painter = painterResource(Res.drawable.ic_like),
                    contentDescription = "Like",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFF5F7FF), CircleShape)
                        .padding(8.dp),
                    tint = Color.Gray // Set the icon color
                )

                // Input Field
                OutlinedTextField(
                    value = comment.value,
                    onValueChange = { comment.value = it },
                    placeholder = {
                        Text("Add a comment...", color = gray_10)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .background(Color(0xFFF5F7FF), RoundedCornerShape(50)),
                    colors = TextFieldWithTransparentTheme(),
                    maxLines = 1,
                    singleLine = true
                )

                // Send Icon
                Icon(
                    painter = painterResource(Res.drawable.ic_post),
                    contentDescription = "Send",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFF5F7FF), CircleShape)
                        .padding(8.dp)
                        .clickable {
                            if (comment.value.isNotBlank()) {
                               // onSendClick(comment)
                                comment.value = ""
                            }
                        },
                    tint = Color.Gray
                )
            }
        }
    }
}