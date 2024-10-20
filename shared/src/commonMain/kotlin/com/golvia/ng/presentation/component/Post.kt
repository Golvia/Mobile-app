package com.golvia.ng.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import com.golvia.ng.businessLayer.domain.FeedsData
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.card_background
import com.golvia.ng.presentation.theme.desc_text_color
import com.golvia.ng.presentation.theme.gray_100
import com.golvia.ng.presentation.theme.gray_50
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.ic_globe
import golvia.shared.generated.resources.sample_post_image
import golvia.shared.generated.resources.sample_profile_image
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@Composable
fun PostContent(
    modifier: Modifier = Modifier,
    feedsData: FeedsData? = null
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

        }

    }

}