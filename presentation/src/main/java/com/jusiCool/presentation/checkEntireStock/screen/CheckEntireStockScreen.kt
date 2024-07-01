package com.jusiCool.presentation.checkEntireStock.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.design_system.component.topbar.JDSMainTopBar
import com.example.design_system.icon_image.icon.GraphIcon
import com.example.design_system.icon_image.icon.SearchIcon
import com.example.design_system.icon_image.image.LogoImage
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.checkEntireStock.component.EntireStocksList
import com.jusiCool.presentation.checkEntireStock.component.EntireStocksListData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val checkEntireStockListRoute = "checkEntireStockListRoute"

fun NavController.navigationToMain() {
    this.navigate(checkEntireStockListRoute)
}

fun NavGraphBuilder.checkEntireStockListRoute(
    navigateToSearch: () -> Unit,
    navigateToMain: () -> Unit,
) {
    composable(route = checkEntireStockListRoute) {
        CheckEntireStockListRoute(
            navigateToSearch = navigateToSearch,
            navigateToMain = navigateToMain
        )
    }
}

@Composable
fun CheckEntireStockListRoute(
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit,
    navigateToMain: () -> Unit,
) {
    CheckEntireStockListRoute(
        modifier = modifier,
        navigateToSearch = navigateToSearch,
        navigateToMain = navigateToMain
    )
}


val tempEntireStocksListData = persistentListOf(
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, -8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 0, 7.9f),
    EntireStocksListData("마이크로소프트", 1, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    EntireStocksListData("마이크로소프트", 1231, 11131, 8160, 7.9f),
)

@Composable
fun CheckEntireStockListScreen(
    modifier: Modifier = Modifier,
    entireStocksListData: ImmutableList<EntireStocksListData>,
    navigateToSearch: () -> Unit,
    navigateToMain: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.GRAY50),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        JDSMainTopBar(
            startIcon = {
                LogoImage(
                    modifier = Modifier.clickableSingle { navigateToMain() }
                )
            },
            betweenIcon = {
                SearchIcon(
                    modifier = Modifier.clickableSingle { navigateToSearch() }
                )
            },
            endIcon = {
                GraphIcon(
                    tint = JDSColor.MAIN
                )
            }
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(entireStocksListData) { item ->
                EntireStocksList(
                    entireStocksListData = item
                )
            }
        }
    }
}

@Preview
@Composable
fun CheckEntireStockListScreenPreview() {
    CheckEntireStockListScreen(
        entireStocksListData = tempEntireStocksListData,
        navigateToSearch = { /*TODO*/ },
        navigateToMain = { /*TODO*/ }
    )
}