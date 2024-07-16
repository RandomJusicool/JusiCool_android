package com.jusiCool.presentation.communityCUD.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.communityCUD.viewmodel.CommunityCUDViewModel

const val communityWritingRoute = "communityWritingRoute"

fun NavController.navigateToCommunityWriting(id: String) {
    this.navigate("$communityWritingRoute/$id")
}

fun NavGraphBuilder.navigateToCommunityWriting(
    popUpBackStack: () -> Unit,
    navigateToCommunity: () -> Unit
) {
    composable("$communityWritingRoute/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: ""
        CommunityWritingRoute(
            id = id,
            popUpBackStack = popUpBackStack,
            navigateToCommunity = navigateToCommunity
        )
    }
}

@Composable
internal fun CommunityWritingRoute(
    modifier: Modifier = Modifier,
    id: String,
    viewModel: CommunityCUDViewModel = hiltViewModel(),
    popUpBackStack: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    CommunityWritingScreen(
        modifier = modifier,
        focusManager = focusManager,
        popUpBackStack = popUpBackStack,
        navigateToCommunity = { title, content ->
            viewModel.postWritingCommunity(
                communityId = id,
                title = title,
                content = content,
            )
            viewModel.title.value = ""
            viewModel.content.value = ""
            navigateToCommunity()
        },
        content = viewModel.content.value,
        title = viewModel.title.value,
    )
}

@Composable
internal fun CommunityWritingScreen(
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    title: String,
    content: String,
    navigateToCommunity: (title: String, content: String) -> Unit,
    popUpBackStack: () -> Unit,
) {
    val (titleTextState, setTitleText) = remember { mutableStateOf(title) }
    val (contentTextState, setContentText) = remember { mutableStateOf(content) }

    LaunchedEffect(Unit) {
        setTitleText(title)
        setContentText(content)
    }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        JusiCoolAndroidTheme { colors, typography ->
            Surface(modifier = modifier) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.GRAY50)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                focusManager.clearFocus()
                            }
                        }
                ) {
                    JDSArrowTopBar(
                        startIcon = {
                            LeftArrowIcon(
                                modifier = Modifier.clickableSingle { popUpBackStack() }
                            )
                        },
                        betweenText = "글 작성"
                    )
                    JDSNoOutLinedTextField(
                        textState = titleTextState,
                        placeHolder = {
                            Text(
                                text = "제목을 입력하세요",
                                style = JDSTypography.titleSmall,
                                color = JDSColor.GRAY200,
                            )
                        },
                        onTextChange = setTitleText,
                        textStyle = typography.titleSmall
                    )
                    JDSNoOutLinedTextField(
                        textState = contentTextState,
                        placeHolder = {
                            Text(
                                text = "내용을 입력하세요",
                                style = JDSTypography.bodyMedium,
                                color = JDSColor.GRAY200,
                            )
                        },
                        onTextChange = setContentText,
                        textStyle = typography.bodySmall
                    )
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
                            text = "올리기",
                            state = if (
                                titleTextState.isNotEmpty()
                                && contentTextState.isNotEmpty()
                            ) ButtonState.Enable
                            else ButtonState.Disable
                        ) {
                            navigateToCommunity(titleTextState, contentTextState)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityWritingScreen() {
    CommunityWritingScreen(
        focusManager = LocalFocusManager.current,
        title = "",
        content = "",
        navigateToCommunity = { _, _ -> }
    ){}
}