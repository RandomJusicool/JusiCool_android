package com.jusiCool.presentation.news.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.news.component.SummaryNews
import com.jusiCool.presentation.news.component.SummaryNewsData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val newsRoute = "newsRoute"

fun NavController.newsRoute() {
    this.navigate(newsRoute)
}

fun NavGraphBuilder.newsRoute(popUpBackStack: () -> Unit) {
    composable(route = newsRoute) {
        NewsRoute(popUpBackStack = popUpBackStack)
    }
}

val TempSummaryNewsData = persistentListOf(
    SummaryNewsData(
        "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
        "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
        "파이낸셜뉴스",
        1
    ),
    SummaryNewsData(
        "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
        "\"고마워요 엔비디아\"",
        "파이낸셜뉴스",
        1
    ),
    SummaryNewsData(
        "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
        "\"고마워요 엔비디아\"...삼성전자",
        "파이낸셜뉴스",
        1
    ),
    SummaryNewsData(
        "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
        "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
        "고고고고고고",
        6
    ),
    SummaryNewsData(
        "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
        "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
        "파이낸셜뉴스",
        1
    ),
    SummaryNewsData(
        "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
        "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
        "파이낸셜뉴스",
        1
    ),
    SummaryNewsData(
        "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
        "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
        "파이낸셜뉴스",
        1
    )
)

@Composable
internal fun NewsRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
) {
    NewsScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        summaryNewsData = TempSummaryNewsData
    )
}

@Composable
internal fun NewsScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    summaryNewsData: ImmutableList<SummaryNewsData>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.GRAY50)
    ) {
        JDSArrowTopBar(
            startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
            betweenText = "뉴스"
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(summaryNewsData) { item ->
                SummaryNews(
                    summaryNewsData = item
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsScreenPreview() {
    NewsScreen(
        popUpBackStack = { /*TODO*/ },
        summaryNewsData = TempSummaryNewsData
    )
}