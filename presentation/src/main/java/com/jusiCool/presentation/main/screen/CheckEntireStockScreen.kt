package com.jusiCool.presentation.main.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSMainTopBar
import com.example.design_system.icon_image.icon.GraphIcon
import com.example.design_system.icon_image.icon.SearchIcon
import com.example.design_system.icon_image.image.LogoImage
import com.example.design_system.theme.color.JDSColor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import com.jusiCool.presentation.main.component.EntireStocksItem
import com.jusiCool.presentation.main.viewModel.MainViewModel
import com.jusiCool.presentation.utill.Event
import kotlinx.coroutines.delay

const val checkEntireStockListRoute = "checkEntireStockListRoute"

fun NavController.navigateToCheckEntireStockList() {
    this.navigate(checkEntireStockListRoute)
}

fun NavGraphBuilder.checkEntireStockListRoute(
    navigateToSearch: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToStockDetail: (String) -> Unit,
) {
    composable(checkEntireStockListRoute) {
        CheckEntireStockListRoute(
            navigateToSearch = navigateToSearch,
            navigateToMain = navigateToMain,
            navigateToStockDetail = navigateToStockDetail,
        )
    }
}

@Composable
fun CheckEntireStockListRoute(
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToStockDetail: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val (isSwipeRefreshLoading, setIsSwipeRefreshLoading) = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isSwipeRefreshLoading)


    CheckEntireStockListScreen(
        modifier = modifier,
        stockData = viewModel.stockList,
        swipeRefreshState = swipeRefreshState,
        onRefresh = {
            viewModel.getStockList()
            setIsSwipeRefreshLoading(true)
        },
        navigateToSearch = navigateToSearch,
        navigateToMain = navigateToMain,
        navigateToStockDetail = navigateToStockDetail,
    )

    LaunchedEffect(isSwipeRefreshLoading) {
        if (isSwipeRefreshLoading) {
            delay(1000L)
            setIsSwipeRefreshLoading(false)
        }
    }

    LaunchedEffect(Unit) {
        getStockList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.stockList.removeRange(0, viewModel.stockList.size)
                viewModel.stockList.addAll(it)
            },
            onFailure = {
                viewModel.stockList.removeRange(0, viewModel.stockList.size)
            }
        )
    }
}

private suspend fun getStockList(
    viewModel: MainViewModel,
    onSuccess: (data: List<GetStockListResponseModel>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getStockListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {
                onFailure()
            }
        }
    }
}

@Composable
fun CheckEntireStockListScreen(
    modifier: Modifier = Modifier,
    swipeRefreshState: SwipeRefreshState,
    stockData: List<GetStockListResponseModel>,
    onRefresh: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToStockDetail: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        onRefresh
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = JDSColor.GRAY50),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            JDSMainTopBar(
                startIcon = {
                    LogoImage(modifier = Modifier.clickableSingle { navigateToMain() })
                },
                betweenIcon = {
                    SearchIcon(modifier = Modifier.clickableSingle { navigateToSearch() })
                },
                endIcon = {
                    GraphIcon(tint = JDSColor.MAIN)
                }
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(stockData) { item ->
                    EntireStocksItem(
                        modifier = Modifier.clickableSingle { navigateToStockDetail(item.code) },
                        entireStocksData = item
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CheckEntireStockListScreenPreview() {

}