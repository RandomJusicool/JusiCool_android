package com.jusiCool.presentation.communityCU.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.button.ButtonState
import com.example.design_system.component.button.JDSButton
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.modifier.padding.paddingHorizontal
import com.example.design_system.component.textfield.JDSNoOutLinedTextField
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.presentation.communityCU.component.CommunityModifierDialog
import com.jusiCool.presentation.communityCU.viewmodel.CommunityCUViewModel

const val communityModifyRoute = "communityModifyRoute"

fun NavController.navigateToCommunityModify(boardId: Long) {
    this.navigate("${communityModifyRoute}/${boardId}")
}

fun NavGraphBuilder.communityModifyRoute(
    popUpBackStack: () -> Unit,
    navigateToCommunityDetail: () -> Unit
) {
    composable("${communityModifyRoute}/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
        if (id != null) {
            CommunityModifyRoute(
                id = id,
                navigateToCommunityDetail = navigateToCommunityDetail,
                popUpBackStack = popUpBackStack
            )
        }
    }
}

@Composable
internal fun CommunityModifyRoute(
    modifier: Modifier = Modifier,
    viewModel: CommunityCUViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    id: Long,
    popUpBackStack: () -> Unit,
    navigateToCommunityDetail: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    CommunityModifyScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToCommunityDetail = { title, content ->
            viewModel.patchCommunityBoard(
                boardId = id,
                title = title,
                content = content
            )
            viewModel.title.value = ""
            viewModel.content.value = ""
            navigateToCommunityDetail()
        },
        title = viewModel.title.value,
        content = viewModel.content.value,
        id = id,
        focusManager = focusManager,
    )
}

@Composable
internal fun CommunityModifyScreen(
    modifier: Modifier = Modifier,
    id: Long,
    navigateToCommunityDetail: (content: String, title: String) -> Unit,
    popUpBackStack: () -> Unit,
    focusManager: FocusManager,
    title: String,
    content: String,
) {
    val (titleTextState, setTitleText) = remember { mutableStateOf(title) }
    val (contentTextState, setContentText) = remember { mutableStateOf(content) }
    val (writingModifierDialogIsVisible, setWritingModifierDialogIsVisible) = remember { mutableStateOf(false) }

    LaunchedEffect(id) {
        setTitleText(title)
        setContentText(content)
    }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        JusiCoolAndroidTheme { colors, typography ->

            Surface(modifier = modifier) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colors.GRAY50)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                focusManager.clearFocus()
                            }
                        }
                ) {
                    if (writingModifierDialogIsVisible) {
                        Dialog(onDismissRequest = { setWritingModifierDialogIsVisible(false) }) {
                            CommunityModifierDialog(
                                checkOnClick = {
                                    setWritingModifierDialogIsVisible(false)
                                },
                                cancelOnClick = { setWritingModifierDialogIsVisible(false) }
                            )
                        }
                    }
                    JDSArrowTopBar(
                        startIcon = {
                            LeftArrowIcon(
                                modifier = modifier.clickableSingle {
                                    popUpBackStack()
                                    setWritingModifierDialogIsVisible(true)
                                }
                            ) },
                        betweenText = "글 수정"
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        JDSNoOutLinedTextField(
                            textState = titleTextState,
                            placeHolder = { },
                            onTextChange = setTitleText,
                            textStyle = typography.titleSmall
                        )
                        JDSNoOutLinedTextField(
                            textState = contentTextState,
                            placeHolder = { },
                            onTextChange = setContentText,
                            textStyle = typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        modifier = Modifier
                            .paddingHorizontal(
                                horizontal = 24.dp,
                                bottom = 44.dp
                            ),
                    ) {
                        JDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            text = "수정하기",
                            state = if (
                                titleTextState.isNotEmpty()
                                && contentTextState.isNotEmpty()
                            ) ButtonState.Enable
                            else ButtonState.Disable
                        ) {
                            navigateToCommunityDetail(titleTextState, contentTextState)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityModifierPre() {
    CommunityModifyRoute(
        id = 0,
        navigateToCommunityDetail = { /*TODO*/ },
        popUpBackStack = { /*TODO*/ }
    )
}