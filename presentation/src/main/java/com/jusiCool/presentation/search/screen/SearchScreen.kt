package com.jusiCool.presentation.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.search.component.PopularStocksSearch
import com.jusiCool.presentation.search.component.PopularStocksSearchData
import com.jusiCool.presentation.search.component.SearchTextField
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val searchRoute = "searchRoute"

fun NavController.navigateToSearch() {
    this.navigate(searchRoute)
}

fun NavGraphBuilder.searchRoute(
    popUpBackStack: () -> Unit,
    navigateToStockDetail: () -> Unit,
) {
    composable(route = searchRoute) {
        SearchRoute(
            popUpBackStack = popUpBackStack,
            navigateToStockDetail = navigateToStockDetail
        )
    }
}

val tempPopularStocksSearchData = persistentListOf(
    PopularStocksSearchData(1, "두산로보틱스", 12.5f),
    PopularStocksSearchData(2, "게임스탑", -2.1f),
    PopularStocksSearchData(3, "애플", -0.4f),
    PopularStocksSearchData(4, "엔비디아", 0.09f),
    PopularStocksSearchData(5, "마이크로소프트", 7.9f)
)

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToStockDetail: () -> Unit,
) {
    SearchScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToStockDetail = navigateToStockDetail,
        popularStocksSearchData = tempPopularStocksSearchData
    )
}


@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToStockDetail: () -> Unit,
    popularStocksSearchData: ImmutableList<PopularStocksSearchData>
) {
    val (stockTextState, setStockTextState) = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.WHITE)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRect(
                        JDSColor.GRAY100,
                        Offset(0f, size.height - 1.dp.toPx()),
                        Size(size.width, 1.dp.toPx())
                    )
                }
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
        ) {
            LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack() })

            SearchTextField(
                textState = stockTextState,
                onTextChange = setStockTextState
            )
        }

        if (stockTextState.isEmpty()) {
            Text(
                modifier = Modifier.padding(start = 24.dp, top = 10.dp, end = 12.dp),
                text = "인기 검색어",
                style = JDSTypography.bodyMedium,
                color = JDSColor.Black
            )

            Column {
                popularStocksSearchData.forEach { item ->
                    PopularStocksSearch(
                        modifier = Modifier.clickableSingle { navigateToStockDetail() },
                        popularStocksSearchData = item
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        popUpBackStack = { },
        popularStocksSearchData = tempPopularStocksSearchData,
        navigateToStockDetail = { },
    )
}