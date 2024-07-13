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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.design_system.theme.JDSTypography
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val communityDetailRoute = "communityDetailRoute"

fun NavController.navigateToCommunityDetail(boardId: Long, communityId: Long, name: String) {
    this.navigate("${communityDetailRoute}/${communityId}/${boardId}/${name}")
}

fun NavGraphBuilder.communityDetailRoute(
    popUpBackStack: () -> Unit,
    navigateToCommunityModify: () -> Unit
) {
    composable("${communityDetailRoute}/{communityId}/{boardId}/{name}") { backStackEntry ->
        val boardId = backStackEntry.arguments?.getString("boardId")?.toLongOrNull()
        val communityId = backStackEntry.arguments?.getString("communityId")?.toLongOrNull()
        val name = backStackEntry.arguments?.getString("name") ?: ""

        if (boardId != null && communityId != null) {
            CommunityDetailRoute(
                name = name,
                boardId = boardId,
                communityId = communityId,
                popUpBackStack = popUpBackStack,
                navigateToCommunityModify = navigateToCommunityModify
            )
        }
    }
}

@Composable
internal fun CommunityDetailRoute(
    modifier: Modifier = Modifier,
    name: String,
    boardId: Long,
    communityId: Long,
    popUpBackStack: () -> Unit,
    navigateToCommunityModify: () -> Unit,
    viewModel: CommunityDetailViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val focusManager = LocalFocusManager.current

    val swipeRefreshLoading by viewModel.swipeRefreshLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = swipeRefreshLoading)
    val likeState = viewModel.like.value

    CommunityDetailScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToCommunityModify = navigateToCommunityModify,
        focusManager = focusManager,
        detailData = viewModel.communityDetail.value,
        commentData = viewModel.communityComment,
        loadStuff = { viewModel.loadStuff() },
        getCommunityDetail = { viewModel.getCommunityDetail(boardId) },
        getCommunityComment = { viewModel.getCommunityComment(boardId) },
        getLike = { viewModel.getLike(boardId) },
        swipeRefreshState = swipeRefreshState,
        deleteCommunityDetail = viewModel::deleteCommunityDetail,
        postLike = viewModel::postLike,
        deleteLike = viewModel::deleteLike,
        postWritingCommunityComment = viewModel::postWritingCommunityComment,
        name = name,
        boardId = boardId,
        communityId = communityId,
        likeState = likeState
    )

    LaunchedEffect(Unit) {
        viewModel.loadStuff()
        viewModel.getLike(boardId)
        viewModel.getCommunityDetail(boardId)
        viewModel.getCommunityComment(boardId)
    }

    LaunchedEffect(Unit) {
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

    LaunchedEffect(Unit) {
        getCommunityDetail(
            viewModel = viewModel,
            onSuccess = {
                viewModel.communityDetail.value = it
            },
            onFailure = { }
        )
    }

    LaunchedEffect(Unit) {
        getLike(
            viewModel = viewModel,
            onSuccess = {
                viewModel.like.value = it
            },
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

private suspend fun getLike(
    viewModel: CommunityDetailViewModel,
    onSuccess: (data: Boolean) -> Unit,
) {
    viewModel.getLikeResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {}
        }
    }
}

@Composable
internal fun CommunityDetailScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    scrollState: ScrollState = rememberScrollState(),
    detailData: GetCommunityBoardDetailResponseModel,
    commentData: List<GetCommunityCommentResponseModel>,
    swipeRefreshState: SwipeRefreshState,
    deleteCommunityDetail: (Long, Long) -> Unit,
    postLike: (Long) -> Unit,
    deleteLike: (Long) -> Unit,
    postWritingCommunityComment: (Long, String) -> Unit,
    name: String,
    boardId: Long,
    communityId: Long,
    likeState: Boolean,
    popUpBackStack: () -> Unit,
    navigateToCommunityModify: () -> Unit,
    loadStuff: () -> Unit,
    getCommunityDetail: () -> Unit,
    getCommunityComment: () -> Unit,
    getLike: () -> Unit,
) {
    val (isHeartClicked, setIsHeartClicked) = remember { mutableStateOf(false) }
    val (commentTextState, setCommentTextChange) = remember { mutableStateOf("") }
    val (writingDeleteDialogIsVisible, setWritingDeleteDialogIsVisible) = remember { mutableStateOf(false) }
    val (like, setLike) = remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(detailData.likes, likeState) {
        setIsHeartClicked(likeState)
        setLike(detailData.likes)
    }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                loadStuff()
                getCommunityDetail()
                getCommunityComment()
                getLike()
                setIsHeartClicked(likeState)
                setLike(detailData.likes)
            }
        ) {
            Column(
                modifier = Modifier
                    .background(color = JDSColor.GRAY50)
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures {
                            focusManager.clearFocus()
                        }
                    }
                    .verticalScroll(scrollState)
            ) {
                if (writingDeleteDialogIsVisible) {
                    Dialog(onDismissRequest = { setWritingDeleteDialogIsVisible(false) }) {
                        CommunityDeleteDialog(
                            checkOnClick = {
                                setWritingDeleteDialogIsVisible(false)
                                deleteCommunityDetail(communityId, boardId)
                                popUpBackStack()
                            },
                            cancelOnClick = { setWritingDeleteDialogIsVisible(false) }
                        )
                    }
                }
                JDSArrowTopBar(
                    startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
                    betweenText = name
                )
                    Spacer(modifier = Modifier.padding(top = 12.dp))
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
                        style = JDSTypography.RegularM,
                        color = JDSColor.MAIN,
                    )
                    Text(
                        modifier = Modifier.clickableSingle { setWritingDeleteDialogIsVisible(true) },
                        text = "삭제하기",
                        style = JDSTypography.RegularM,
                        color = JDSColor.ERROR,
                    )
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = detailData.title,
                    style = JDSTypography.titleSmall
                )
                Text(
                    modifier = Modifier.paddingHorizontal(horizontal = 24.dp, top = 24.dp),
                    text = detailData.content,
                    style = JDSTypography.bodySmall
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                HeartOutlinedButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = like.toString(),
                    startIcon = { HeartIcon(tint = if (isHeartClicked) JDSColor.WHITE else JDSColor.GRAY400) },
                    onClick = {
                        if (isHeartClicked) {
                            coroutineScope.launch {
                                setIsHeartClicked(false)
                                deleteLike(boardId)
                                getLike()
                                delay(100L)
                                setLike(like - 1)
                            }
                        } else {
                            coroutineScope.launch {
                                setIsHeartClicked(true)
                                postLike(boardId)
                                getLike()
                                delay(100L)
                                setLike(like + 1)
                            }
                        }
                    },
                    textColor = if (isHeartClicked) JDSColor.WHITE else JDSColor.GRAY400,
                    backgroundColor = if (isHeartClicked) JDSColor.MAIN else Color.Unspecified,
                    outLineColor = if (isHeartClicked) JDSColor.MAIN else JDSColor.GRAY400
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
                    onValueChange = setCommentTextChange,
                    value = commentTextState,
                    singleLine = false,
                    onButtonClicked = {
                        if (commentTextState.isNotEmpty()) {
                            coroutineScope.launch {
                                postWritingCommunityComment(boardId, commentTextState)
                                delay(100L)
                                getCommunityComment()
                                setCommentTextChange("")
                            }
                        }
                    }
                )
                CommentCardList(data = commentData)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommunityDetailPre() {
    val mockDetailData = GetCommunityBoardDetailResponseModel(
        communityName = "Mock Comunity Name",
        title = "Mock Title",
        content = "Mock content for preview purposes.",
        likes = 42
    )

    val mockCommentData = listOf(
        GetCommunityCommentResponseModel(
            name = "오은찬",
            content = "Mock comment 1",
        ),
        GetCommunityCommentResponseModel(
            name = "이명훈",
            content = "Mock comment 1",
        )
    )

    CommunityDetailScreen(
        focusManager = LocalFocusManager.current,
        detailData = mockDetailData,
        commentData = mockCommentData,
        swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false),
        deleteCommunityDetail = { _, _ -> },
        postLike = {},
        deleteLike = {},
        postWritingCommunityComment = { _, _ -> },
        name = "Mock Community",
        boardId = 1L,
        communityId = 1L,
        loadStuff = {},
        getCommunityDetail = {},
        getCommunityComment = {},
        getLike = {},
        popUpBackStack = {},
        navigateToCommunityModify = {},
        likeState = false
    )
}
