package com.jusiCool.presentation.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.orderHistory.screen.OrderHistoryRoute
import com.jusiCool.presentation.orderHistory.screen.OrderHistoryScreen
import com.jusiCool.presentation.orderHistory.screen.tempMyStocksOrderHistoryData
import com.jusiCool.presentation.orderHistory.screen.tempMyStocksOrderReservationData
import com.jusiCool.presentation.search.component.PopularStocksSearch
import com.jusiCool.presentation.search.component.PopularStocksSearchData
import com.jusiCool.presentation.search.component.SearchTopBar
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val searchRoute = "searchRoute"

fun NavController.searchRoute() {
    this.navigate(searchRoute)
}

fun NavGraphBuilder.searchRoute(popUpBackStack: () -> Unit) {
    composable(route = searchRoute) {
        SearchRoute(popUpBackStack = popUpBackStack)
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
) {
    SearchScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        popularStocksSearchData = tempPopularStocksSearchData
    )
}



@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    popularStocksSearchData: ImmutableList<PopularStocksSearchData>
) {
    val (stockTextState, setStockTextState) = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.WHITE)
    ) {
        SearchTopBar(
            startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
            textState = stockTextState,
            onTextChange = setStockTextState
        )

        if(stockTextState.isEmpty()) {
            Text(
                modifier = Modifier.padding(start = 24.dp, top = 10.dp, end = 12.dp),
                text = "인기 검색어",
                style = JDSTypography.bodyMedium,
                color = JDSColor.Black
            )

            Column {
                popularStocksSearchData.forEach { item ->
                    PopularStocksSearch(popularStocksSearchData = item)
                }
            }
        }
    }

}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        popUpBackStack = { /*TODO*/},
        popularStocksSearchData = tempPopularStocksSearchData
    )
}
