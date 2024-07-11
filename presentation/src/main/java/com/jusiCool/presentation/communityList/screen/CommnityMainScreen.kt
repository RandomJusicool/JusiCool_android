package com.jusiCool.presentation.communityList.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel
import com.jusiCool.presentation.communityList.component.CommunityMainList
import com.jusiCool.presentation.communityList.viewModel.CommunityListViewModel
import com.jusiCool.presentation.utill.Event

const val communityListRoute = "communityListRoute"

fun NavController.navigateToCommunityList() {
    this.navigate(communityListRoute)
}

fun NavGraphBuilder.communityListRoute(
    popUpBackStack: () -> Unit,
    navigateToCommunity: (Long) -> Unit,
) {
    composable(communityListRoute) {
        CommunityListRoute(
            popUpBackStack = popUpBackStack,
            navigateToCommunity = navigateToCommunity
        )
    }
}

@Composable
internal fun CommunityListRoute(
    modifier: Modifier = Modifier,
    viewModel: CommunityListViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    popUpBackStack: () -> Unit,
    navigateToCommunity: (Long) -> Unit,
) {
    val swipeRefreshLoading by viewModel.swipeRefreshLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = swipeRefreshLoading)

    CommunityListScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToCommunity = navigateToCommunity,
        data = viewModel.communityList,
        loadStuff = viewModel::loadStuff,
        swipeRefreshState = swipeRefreshState,
        getCommunityList = viewModel::getCommunityList
    )
    LaunchedEffect(Unit) {
        viewModel.getCommunityList()
    }

    LaunchedEffect(Unit) {
        getCommunityList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.communityList.removeRange(0, viewModel.communityList.size)
                viewModel.communityList.addAll(it)
            },
            onFailure = {
                viewModel.communityList.removeRange(0, viewModel.communityList.size)
            }
        )
    }
}

private suspend fun getCommunityList(
    viewModel: CommunityListViewModel,
    onSuccess: (data: List<GetCommunityListResponseModel>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getCommunityListResponse.collect { response ->
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
internal fun CommunityListScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunity: (Long) -> Unit,
    data: List<GetCommunityListResponseModel>,
    loadStuff: () -> Unit,
    swipeRefreshState: SwipeRefreshState,
    getCommunityList: () -> Unit
) {
    JusiCoolAndroidTheme { colors, _ ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                loadStuff()
                getCommunityList()
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(colors.GRAY50)
            ) {
                Column {
                    JDSArrowTopBar(
                        startIcon = {
                            LeftArrowIcon(
                                modifier = Modifier.clickableSingle { popUpBackStack() }
                            )
                        },
                        betweenText = "커뮤니티 목록"
                    )
                    CommunityMainList(
                        data = data,
                        navigateToCommunity = navigateToCommunity
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityMainScreenPre() {
    CommunityListScreen(
        popUpBackStack = {  },
        navigateToCommunity = {  },
        data = listOf(),
        loadStuff = {  },
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    ){}
}