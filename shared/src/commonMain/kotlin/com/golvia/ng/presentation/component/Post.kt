package com.golvia.ng.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.businessLayer.domain.Comments
import com.golvia.ng.businessLayer.domain.FeedsData
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.desc_text_color
import com.golvia.ng.presentation.theme.gray_10
import com.golvia.ng.presentation.theme.gray_100
import com.golvia.ng.presentation.theme.gray_5
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.gray_dot
import golvia.shared.generated.resources.ic_comment_icon
import golvia.shared.generated.resources.ic_globe
import golvia.shared.generated.resources.ic_like_icon
import golvia.shared.generated.resources.ic_like_small_icon
import golvia.shared.generated.resources.ic_share_icon
import golvia.shared.generated.resources.sample_post_image
import golvia.shared.generated.resources.sample_profile_image
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@Composable
fun PostContent(
    modifier: Modifier = Modifier,
    feedsData: FeedsData? = null,
    isFromDetail: Boolean = false,
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
){
    val cornerRadius = RoundedCornerShape(8.dp)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(cornerRadius),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Black,
            disabledContainerColor = Color.White,
            disabledContentColor = Black
        ),
        shape = cornerRadius
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )
        {
            Row(
                modifier = Modifier.padding(top = 12.dp, start = 8.dp, end = 8.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                CircularImageView(
                    image = painterResource(Res.drawable.sample_profile_image),
                    borderColor = LightGray,
                    borderWidth = 0.5.dp,
                    size = 40.dp,
                )

                Column(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = feedsData?.profileName.orEmpty(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Black
                        ),
                        fontFamily = LatoTypography().bodyMedium.fontFamily
                    )
                    Text(
                        text = feedsData?.agent.orEmpty(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = gray_100
                        ),
                        fontFamily = LatoTypography().bodyMedium.fontFamily
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_globe),
                        contentDescription = "globe",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = feedsData?.description.orEmpty(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        fontWeight = FontWeight(400),
                        color = desc_text_color
                    ),
                    textAlign = TextAlign.Justify,
                    fontFamily = LatoTypography().headlineLarge.fontFamily
                )
                Spacer_12dp()
                SquareImageView(
                    image = painterResource(Res.drawable.sample_post_image),
                    borderColor = LightGray,
                    borderWidth = 0.5.dp,
                    size = 128.dp,
                )
            }

            Spacer_8dp()

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_like_icon),
                        contentDescription = "messages",
                        modifier = Modifier.padding(end = 8.dp).size(20.dp)
                    )
                    Text(
                        text = "${feedsData?.likes?.size ?: 0} Likes",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = gray_5
                        ),
                        fontFamily = LatoTypography().bodyMedium.fontFamily
                    )
                }

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(Res.drawable.gray_dot),
                        contentDescription = "messages",
                        modifier = Modifier.padding(end = 8.dp).size(6.dp)
                    )
                    Text(
                        text = "${feedsData?.comments?.size ?: 0} Comments",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = gray_5
                        ),
                        fontFamily = LatoTypography().bodyMedium.fontFamily
                    )
                }

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(Res.drawable.gray_dot),
                        contentDescription = "messages",
                        modifier = Modifier.padding(end = 8.dp).size(6.dp)
                    )
                    Text(
                        text = "${feedsData?.comments?.size ?: 0} Share",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = gray_5
                        ),
                        fontFamily = LatoTypography().bodyMedium.fontFamily
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(top = 12.dp, start = 8.dp, end = 8.dp),
                color = gray_10,
                thickness = 0.5.dp)

            Spacer_8dp()
           if (!isFromDetail){
               Row(
                   modifier = Modifier.fillMaxWidth()
                       .padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween
               ){
                   CircularImageView(
                       image = painterResource(Res.drawable.sample_profile_image),
                       borderColor = LightGray,
                       borderWidth = 0.5.dp,
                       size = 40.dp,
                   )

                   Spacer_4dp()
                   RoundedFlatPostButton(
                       modifier = Modifier.weight(0.4f),
                       text = "Like",
                       tintColor = Black,
                       containerColor = Color(0xFFF6F9FF),
                       icon = painterResource(Res.drawable.ic_like_small_icon),
                       onClick = {
                           onLikeClick()
                           // Todo navigate to post screen
                       }
                   )
                   Spacer_4dp()
                   RoundedFlatPostButton(
                       modifier = Modifier.weight(0.6f),
                       text = "Comment",
                       tintColor = Black,
                       containerColor = Color(0xFFF6F9FF),
                       icon = painterResource(Res.drawable.ic_comment_icon),
                       onClick = {
                           onCommentClick()
                           // Todo navigate to post screen
                       }
                   )
                   Spacer_4dp()
                   RoundedFlatPostButton(
                       modifier = Modifier.weight(0.6f).width(50.dp),
                       text = "Share",
                       tintColor = Black,
                       containerColor = Color(0xFFF6F9FF),
                       icon = painterResource(Res.drawable.ic_share_icon),
                       onClick = {
                           onShareClick()
                           // Todo navigate to post screen
                       }
                   )
               }
           }

        }

    }

}


@Composable
fun FeedItem(comment: Comments) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Profile Image
        CircularImageView(
            image = painterResource(Res.drawable.sample_profile_image),
            borderColor = LightGray,
            borderWidth = 0.5.dp,
            size = 40.dp,
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Content Section
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFFF1F4F9), shape = RoundedCornerShape(6.dp))
        ) {
            // Profile Name
            Text(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                text = comment.name.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Comments Section
            Spacer_4dp()
            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = comment.comment ?: "",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}