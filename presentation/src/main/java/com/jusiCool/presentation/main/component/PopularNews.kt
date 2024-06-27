package com.jusiCool.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.icon_image.icon.RightChevronIcon
import com.example.design_system.icon_image.image.EllipseImage
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.main.screen.tempSummaryNewsData

data class SummaryNewsData(
    val imageUrl: String,
    val summaryArticle: String,
    val publisher: String,
    val passedTime: Int
)

@Composable
fun PopularNews(
    modifier: Modifier = Modifier,
    summaryNewsData: SummaryNewsData,
    navigateToNews: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = JDSColor.WHITE, shape = RoundedCornerShape(size = 12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "대표 인기 뉴스",
            style = JDSTypography.subTitle,
            color = JDSColor.Black
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
            Image(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                painter = rememberAsyncImagePainter(summaryNewsData.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(
                text = summaryNewsData.summaryArticle,
                style = JDSTypography.bodyMedium,
                color = JDSColor.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${summaryNewsData.passedTime}시간 전",
                    style = JDSTypography.label,
                    color = JDSColor.GRAY400
                )

                EllipseImage(modifier = Modifier.size(3.dp))

                Text(
                    text = summaryNewsData.publisher,
                    style = JDSTypography.label,
                    color = JDSColor.GRAY400
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.clickableSingle { navigateToNews() },
                text = "뉴스 더보기",
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            RightChevronIcon()
        }
    }
}

@Preview
@Composable
fun PopularNewsPreview() {
    PopularNews(
        modifier = Modifier.width(312.dp),
        summaryNewsData = tempSummaryNewsData,
        navigateToNews = { /*TODO*/ }
    )
}