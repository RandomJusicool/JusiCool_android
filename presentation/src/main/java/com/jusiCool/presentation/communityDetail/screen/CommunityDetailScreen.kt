package com.jusiCool.presentation.communityDetail.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.modifier.padding.paddingHorizontal
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.HeartIcon
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jusiCool.domain.model.board.response.GetCommunityBoardDetailResponseModel
import com.jusiCool.domain.model.comment.response.GetCommunityCommentResponseModel
import com.jusiCool.presentation.communityDetail.component.CommentCardList
import com.jusiCool.presentation.communityDetail.component.CommentTextField
import com.jusiCool.presentation.communityDetail.component.CommunityDeleteDialog
import com.jusiCool.presentation.communityDetail.component.HeartOutlinedButton
import com.jusiCool.presentation.communityDetail.viewModel.CommunityDetailViewModel
import com.jusiCool.presentation.utill.Event

const val communityDetailRoute = "communityDetailRoute"

fun NavController.navigateToCommunityDetail() {
    this.navigate(communityDetailRoute)
}

fun NavGraphBuilder.communityDetailRoute(
    popUpBackStack: () -> Unit,
    navigateToCommunityModify: () -> Unit
) {
    composable( communityDetailRoute) {
        communityDetailRoute(
            popUpBackStack = popUpBackStack,
            navigateToCommunityModify = navigateToCommunityModify
        )
    }
}

@Composable
internal fun CommunityDetailRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunityModify: () -> Unit,
    viewModel: CommunityDetailViewModel = hiltViewModel(LocalContext.current as ComponentActivity),

) {
    val focusManager = LocalFocusManager.current

    val swipeRefreshLoading by viewModel.swipeRefreshLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = swipeRefreshLoading)

    CommunityDetailScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToCommunityModify = navigateToCommunityModify,
        focusManager = focusManager,
        detailData = viewModel.communityDetail.value,
        commentData = viewModel.communityComment,
        loadStuff = viewModel::loadStuff,
        swipeRefreshState = swipeRefreshState,
        getCommunityDetail = viewModel::getCommunityDetail,
        getCommunityComment = viewModel::getCommunityComment
    )


    LaunchedEffect(Unit) {
        getCommunityDetail(
            viewModel = viewModel,
            onSuccess = {
                viewModel.communityDetail.value = it
            },
            onFailure = { TODO() }
        )
        getCommunityComment(
            viewModel = viewModel,
            onSuccess = {
                viewModel.communityComment.removeRange(0, viewModel.communityComment.size)
                viewModel.communityComment.addAll(it)
            },
            onFailure = {
                viewModel.communityComment.removeRange(0, viewModel.communityComment.size)
            }
        )
    }
}

private suspend fun getCommunityDetail(
    viewModel: CommunityDetailViewModel,
    onSuccess: (data: GetCommunityBoardDetailResponseModel) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getCommunityBoardDetailResponse.collect { response ->
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

private suspend fun getCommunityComment(
    viewModel: CommunityDetailViewModel,
    onSuccess: (data: List<GetCommunityCommentResponseModel>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getCommunityCommentResponse.collect { response ->
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
internal fun CommunityDetailScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunityModify: () -> Unit,
    focusManager: FocusManager,
    scrollState: ScrollState = rememberScrollState(),
    detailData: GetCommunityBoardDetailResponseModel,
    commentData: List<GetCommunityCommentResponseModel>,
    loadStuff: () -> Unit,
    swipeRefreshState: SwipeRefreshState,
    getCommunityDetail: () -> Unit,
    getCommunityComment: () -> Unit
) {
    val (isHeartClicked,setIsHeartClicked) = remember { mutableStateOf(false) }
    val (commentTextState, onCommentTextChange) = remember { mutableStateOf("") }
    val (writingDeleteDialogIsVisible, setWritingDeleteDialogIsVisible) = remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        JusiCoolAndroidTheme { colors, typography ->
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    loadStuff()
                    getCommunityDetail()
                    getCommunityComment()
                }
            ) {
                Box(modifier = modifier.background(color = colors.GRAY50)) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures {
                                    focusManager.clearFocus()
                                }
                            }
                    ) {
                        if (writingDeleteDialogIsVisible) {
                            Dialog(onDismissRequest = { setWritingDeleteDialogIsVisible(false) }) {
                                CommunityDeleteDialog(
                                    checkOnClick = {
                                        setWritingDeleteDialogIsVisible(false)
                                        // 통신 로직 작성 후 수정
                                    },
                                    cancelOnClick = { setWritingDeleteDialogIsVisible(false) }
                                )
                            }
                        }
                        JDSArrowTopBar(
                            startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
                            betweenText = ""
                        )
                        Spacer(modifier = Modifier.padding(top = 27.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = Modifier.clickableSingle { navigateToCommunityModify() },
                                text = "수정하기",
                                style = typography.RegularM,
                                color = colors.MAIN,
                            )
                            Text(
                                modifier = Modifier.clickableSingle {
                                    setWritingDeleteDialogIsVisible(
                                        true
                                    )
                                }, // 후에 통신 로직 작성
                                text = "삭제하기",
                                style = typography.RegularM,
                                color = colors.ERROR,
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            text = detailData.title,
                            style = typography.titleSmall
                        )
                        Text(
                            modifier = Modifier.paddingHorizontal(horizontal = 24.dp, top = 24.dp),
                            text = detailData.content,
                            style = typography.bodySmall
                        )
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        HeartOutlinedButton(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = detailData.likes.toString(),
                            startIcon = { HeartIcon(tint = if (isHeartClicked) colors.WHITE else colors.GRAY400) },
                            onClick = { setIsHeartClicked(!isHeartClicked) }, // 후에 통신 로직 작성
                            textColor = if (isHeartClicked) colors.WHITE else colors.GRAY400,
                            backgroundColor = if (isHeartClicked) colors.MAIN else Color.Unspecified,
                            outLineColor = if (isHeartClicked) colors.MAIN else colors.GRAY400
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .paddingHorizontal(horizontal = 24.dp, top = 28.dp)
                                .height(1.dp)
                                .background(
                                    color = JDSColor.GRAY100,
                                    shape = RoundedCornerShape(size = 5.dp)
                                )
                        )
                        CommentTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .paddingHorizontal(
                                    horizontal = 24.dp,
                                    top = 14.dp
                                ),
                            placeholder = "댓글을 작성해보세요",
                            isDisabled = false,
                            onValueChange = onCommentTextChange,
                            value = commentTextState,
                            singleLine = false,
                            onButtonClicked = { } // 후에 통신 로직 작성
                        )
                        CommentCardList(data = commentData)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityDetailPre() {

}