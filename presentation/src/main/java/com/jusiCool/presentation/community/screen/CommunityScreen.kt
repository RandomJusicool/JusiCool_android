package com.jusiCool.presentation.community.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel
import com.jusiCool.presentation.community.component.CommunityList
import com.jusiCool.presentation.community.component.WritingCommunityButton
import com.jusiCool.presentation.community.viewModel.CommunityViewModel
import com.jusiCool.presentation.communityList.viewModel.CommunityListViewModel
import com.jusiCool.presentation.utill.Event

const val communityRoute = "communityRoute"

fun NavController.navigateToCommunity() {
    this.navigate(communityRoute)
}

fun NavGraphBuilder.communityRoute(
    navigateToCommunityWriting: () -> Unit,
    navigateToCommunityDetail: () -> Unit,
    popUpBackStack: () -> Unit,
) {
    composable(communityRoute) {
        CommunityRoute(
            navigateToDetailCommunity = navigateToCommunityDetail,
            navigateToCommunityWriting = navigateToCommunityWriting,
            popUpBackStack = popUpBackStack,
        )
    }
}

@Composable
internal fun CommunityRoute(
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    topBarViewModel: CommunityListViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    navigateToDetailCommunity: () -> Unit,
    navigateToCommunityWriting: () -> Unit,
    popUpBackStack: () -> Unit,
) {
    val swipeRefreshLoading by viewModel.swipeRefreshLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = swipeRefreshLoading)

    CommunityScreen(
        modifier = modifier,
        navigateToDetailCommunity = {
            viewModel.communityId.longValue = it
            navigateToDetailCommunity()
        },
        navigateToCommunityWriting = navigateToCommunityWriting,
        popUpBackStack = popUpBackStack,
        topBarTitleData = topBarViewModel::getCommunityListName,
        data = viewModel.communityListBoard,
        loadStuff = viewModel::loadStuff,
        swipeRefreshState = swipeRefreshState,
        getCommunityListBoard = viewModel::getListBoard
    )

    LaunchedEffect(Unit) {
        getCommunityListBoard(
            viewModel = viewModel,
            onSuccess = {
                viewModel.communityListBoard.removeRange(0, viewModel.communityListBoard.size)
                viewModel.communityListBoard.addAll(it)
            },
            onFailure = {
                viewModel.communityListBoard.removeRange(0, viewModel.communityListBoard.size)
            }
        )
    }
}

private suspend fun getCommunityListBoard(
    viewModel: CommunityViewModel,
    onSuccess: (data: List<GetCommunityBoardListResponseModel>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getCommunityListBoardResponse.collect { response ->
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
internal fun CommunityScreen(
    modifier: Modifier = Modifier,
    navigateToDetailCommunity: (Long) -> Unit,
    popUpBackStack: () -> Unit,
    topBarTitleData: () -> String,
    data: List<GetCommunityBoardListResponseModel>,
    loadStuff: () -> Unit,
    swipeRefreshState: SwipeRefreshState,
    getCommunityListBoard: () -> Unit,
    navigateToCommunityWriting: () -> Unit,
    ) {
    JusiCoolAndroidTheme { colors, _ ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                loadStuff()
                getCommunityListBoard()
            }
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.GRAY50)
            ) {
                Column {
                    JDSArrowTopBar(
                        startIcon = {
                            LeftArrowIcon(
                                modifier = Modifier.clickableSingle { popUpBackStack() }
                            )
                        },
                        betweenText = topBarTitleData()
                    )
                    CommunityList(
                        data = data,
                        navigateToDetailCommunity = { navigateToDetailCommunity(it) }
                    )
                }
                WritingCommunityButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(
                            end = 24.dp,
                            bottom = 24.dp
                        ),
                    onClick = navigateToCommunityWriting
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommunityScreenPre() {
    CommunityScreen(
        navigateToDetailCommunity = {  },
        popUpBackStack = {  },
        topBarTitleData = { "" },
        data = listOf(),
        loadStuff = {  },
        swipeRefreshState = SwipeRefreshState(false),
        getCommunityListBoard = {  }
    ) {}
}