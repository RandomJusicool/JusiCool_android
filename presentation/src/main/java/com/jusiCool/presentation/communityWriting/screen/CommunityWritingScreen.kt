package com.jusiCool.presentation.communityWriting.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

const val communityWritingRoute = "communityWritingRoute"

fun NavController.communityWritingRoute() {
    this.navigate(communityWritingRoute)
}

fun NavGraphBuilder.communityWritingRoute(popUpBackStack: () -> Unit) {
    composable(route = communityWritingRoute) {
        CommunityWritingRoute(popUpBackStack = popUpBackStack)
    }
}

@Composable
internal fun CommunityWritingRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    CommunityWritingScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        focusManager = focusManager
    )
}

@Composable
internal fun CommunityWritingScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    focusManager: FocusManager,
) {
    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        JusiCoolAndroidTheme { colors, typography ->
            val (titleTextState, setTitleText) = remember { mutableStateOf("") }
            val (contentTextState, setContentText) = remember { mutableStateOf("") }

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
                            onClick = popUpBackStack, // 후에 세부 코드 추가할 에정
                            state =
                            if (
                                titleTextState.isNotEmpty()
                                && contentTextState.isNotEmpty()
                            ) ButtonState.Enable
                            else ButtonState.Disable
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityWritingScreen() {
    CommunityWritingRoute {}
}