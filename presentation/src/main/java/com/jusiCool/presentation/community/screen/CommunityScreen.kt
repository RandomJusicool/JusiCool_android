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
import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel
import com.jusiCool.presentation.community.component.CommunityList
import com.jusiCool.presentation.community.component.WritingCommunityButton
import com.jusiCool.presentation.community.viewModel.CommunityViewModel
import com.jusiCool.presentation.communityList.viewModel.CommunityListViewModel
import com.jusiCool.presentation.utill.Event

const val communityRoute = "communityRoute"

fun NavController.navigateToCommunity(id: Long) {
    this.navigate("${communityRoute}/${id}")
}

fun NavGraphBuilder.communityRoute(
    navigateToCommunityWriting: (Long) -> Unit,
    navigateToCommunityDetail: (Long) -> Unit,
    popUpBackStack: () -> Unit,
) {
    composable("${communityRoute}/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
        if (id != null) {
            CommunityRoute(
                id = id,
                navigateToDetailCommunity = navigateToCommunityDetail,
                navigateToCommunityWriting = navigateToCommunityWriting,
                popUpBackStack = popUpBackStack,
            )
        }
    }
}

@Composable
internal fun CommunityRoute(
    modifier: Modifier = Modifier,
    id: Long,
    viewModel: CommunityViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    communityViewModel: CommunityListViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    navigateToDetailCommunity: (Long) -> Unit,
    navigateToCommunityWriting: (Long) -> Unit,
    popUpBackStack: () -> Unit,
) {
    val swipeRefreshLoading by viewModel.swipeRefreshLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = swipeRefreshLoading)

    CommunityScreen(
        modifier = modifier,
        navigateToDetailCommunity =  navigateToDetailCommunity,
        navigateToCommunityWriting = navigateToCommunityWriting,
        popUpBackStack = popUpBackStack,
        topBarTitleData = communityViewModel::getCommunityListName,
        boardData = viewModel.communityListBoard,
        loadStuff = viewModel::loadStuff,
        swipeRefreshState = swipeRefreshState,
        getCommunityListBoard = viewModel::getListBoard,
        id = id,
        communityData = communityViewModel.communityData.value
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
    id: Long,
    getCommunityListBoard: (Long) -> Unit,
    navigateToCommunityWriting: (Long) -> Unit,
    navigateToDetailCommunity: (Long) -> Unit,
    popUpBackStack: () -> Unit,
    topBarTitleData: () -> String,
    boardData: List<GetCommunityBoardListResponseModel>,
    communityData: GetCommunityListResponseModel,
    swipeRefreshState: SwipeRefreshState,
    loadStuff: () -> Unit,
    ) {
    LaunchedEffect(Unit) {
        getCommunityListBoard(id)
    }

    JusiCoolAndroidTheme { colors, _ ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                loadStuff()
                getCommunityListBoard(id)
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
                        data = boardData,
                        navigateToDetailCommunity = navigateToDetailCommunity
                    )
                }
                WritingCommunityButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(
                            end = 24.dp,
                            bottom = 24.dp
                        ),
                    navigateToCommunityWriting = navigateToCommunityWriting,
                    data = communityData
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
        navigateToCommunityWriting = {  },
        popUpBackStack = {  },
        topBarTitleData = { "자바보단 코틀린" },
        boardData = listOf(),
        loadStuff = {  },
        swipeRefreshState = SwipeRefreshState(false),
        getCommunityListBoard = {  },
        id = 0,
        communityData = GetCommunityListResponseModel(
            id = 0,
            board_num = 0,
            name = ""
        )
    )
}