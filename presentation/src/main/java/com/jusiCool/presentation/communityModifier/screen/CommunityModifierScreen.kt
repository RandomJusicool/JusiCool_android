package com.jusiCool.presentation.communityModifier.screen

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
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.presentation.community.component.CommunityListItemTemData

const val communityModifierRoute = "communityModifierRoute"

fun NavController.communityModifierRoute() {
    this.navigate(communityModifierRoute)
}

fun NavGraphBuilder.communityModifierRoute(popUpBackStack: () -> Unit) {
    composable(route = communityModifierRoute) {
        CommunityModifierRoute(popUpBackStack = popUpBackStack)
    }
}

@Composable
internal fun CommunityModifierRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    ) {
    val focusManager = LocalFocusManager.current

    CommunityModifierScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        focusManager = focusManager,
        initialData = CommunityListItemTemData(
            title = "커뮤니티는공통의관심사목표가치혹은지리적커뮤니",
            content = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인",
            name = "이명훈",
            started_date = "06.20",
            started_time = "17:06",
            heart_count = 12,
            comment_count = 13
        )
    )
}

@Composable
internal fun CommunityModifierScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    focusManager: FocusManager,
    initialData: CommunityListItemTemData
) {
    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        JusiCoolAndroidTheme { colors, typography ->
            val (titleTextState, setTitleText) = remember { mutableStateOf(initialData.title) }
            val (contentTextState, setContentText) = remember { mutableStateOf(initialData.content) }

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
                    JDSArrowTopBar(
                        startIcon = { LeftArrowIcon(modifier = modifier.clickableSingle { popUpBackStack() }) },
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
                            onClick = popUpBackStack,
                            state = if (
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
private fun CommunityModifierPre() {
    CommunityModifierRoute(popUpBackStack = { /*TODO*/ })
}