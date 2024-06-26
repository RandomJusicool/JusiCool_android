package com.jusiCool.presentation.news.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.design_system.icon_image.image.EllipseImage
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

data class SummaryNewsData(
    val imageUrl: String,
    val summaryArticle: String,
    val publisher: String,
    val passedTime: Int
)

@Composable
fun SummaryNews(
    modifier: Modifier = Modifier,
    summaryNewsData: SummaryNewsData
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = JDSColor.WHITE, shape = RoundedCornerShape(size = 12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.56f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = summaryNewsData.summaryArticle,
                style = JDSTypography.bodyMedium,
                color = JDSColor.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
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

        Image(
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(12.dp)),
            painter = rememberAsyncImagePainter(summaryNewsData.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun SummaryNewsPreview() {
    SummaryNews(
        modifier = Modifier.width(312.dp),
        summaryNewsData = SummaryNewsData(
            "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
            "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
            "파이낸셜뉴스",
            1
        )
    )
}