package com.jusiCool.presentation.communityDetail.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.modifier.padding.paddingHorizontal
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.HeartIcon
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.icon_image.icon.SendIcon
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.community.component.CommunityListItemTemData
import com.jusiCool.presentation.communityDetail.component.CommentCard
import com.jusiCool.presentation.communityDetail.component.CommentCardList
import com.jusiCool.presentation.communityDetail.component.CommentTextField
import com.jusiCool.presentation.communityDetail.component.CommunityDeleteDialog
import com.jusiCool.presentation.communityDetail.component.HeartOutlinedButton
import com.jusiCool.presentation.communityDetail.component.TemCommentData
import com.jusiCool.presentation.communityList.component.TemList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val communityDetailRoute = "communityDetailRoute"

fun NavController.communityDetailRoute() {
    this.navigate(communityDetailRoute)
}

fun NavGraphBuilder.communityDetailRoute(
    popUpBackStack: () -> Unit,
    navigateToCommunityDetail: () -> Unit
) {
    composable(route = communityDetailRoute) {
        communityDetailRoute(
            popUpBackStack = popUpBackStack,
            navigateToCommunityDetail = navigateToCommunityDetail
        )
    }
}

@Composable
internal fun CommunityDetailRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunityDetail: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    CommunityDetailScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToCommunityDetail = navigateToCommunityDetail,
        focusManager = focusManager,
        data1 = TemList(
            company = "마이크로소프트 커뮤니티",
            count = 1
        ),
        data2 = CommunityListItemTemData(
            title = "커뮤니티는공통의관심사목표가치혹은지리적커뮤니",
            content = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인",
            name = "이명훈",
            started_date = "06.20",
            started_time = "17:06",
            heart_count = 12,
            comment_count = 13
        ),
        data3 = persistentListOf(
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            ),
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            ),
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            ),
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            ),
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            ),
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            ),
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            ),
            TemCommentData(
                name = "o0뀨0o",
                comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
            )
        )
    )
}

@Composable
internal fun CommunityDetailScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunityDetail: () -> Unit,
    focusManager: FocusManager,
    scrollState: ScrollState = rememberScrollState(),
    data1: TemList,
    data2: CommunityListItemTemData,
    data3: ImmutableList<TemCommentData>
) {
    var isHeartClicked by remember { mutableStateOf(false) }
    val (commentTextState, onCommentTextChange) = remember { mutableStateOf("") }
    val (writingDeleteDialogIsVisible, setWritingDeleteDialogIsVisible) = remember { mutableStateOf(false)
    }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        JusiCoolAndroidTheme { colors, typography ->
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
                        betweenText = data1.company
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
                            modifier = Modifier.clickableSingle { navigateToCommunityDetail() },
                            text = "수정하기",
                            style = typography.RegularM,
                            color = colors.MAIN,
                        )
                        Text(
                            modifier = Modifier.clickableSingle { setWritingDeleteDialogIsVisible(true) }, // 후에 통신 로직 작성
                            text = "삭제하기",
                            style = typography.RegularM,
                            color = colors.ERROR,
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = data2.title,
                        style = typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.paddingHorizontal(horizontal = 24.dp, top = 24.dp),
                        text = data2.content,
                        style = typography.bodySmall
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    HeartOutlinedButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = data2.heart_count.toString(),
                        startIcon = { HeartIcon(tint = if (isHeartClicked) colors.WHITE else colors.GRAY400) },
                        onClick = { isHeartClicked = !isHeartClicked }, // 후에 통신 로직 작성
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
                    CommentCardList(data = data3)
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityDetailPre() {
    CommunityDetailRoute(popUpBackStack = { /*TODO*/ }) {

    }
}