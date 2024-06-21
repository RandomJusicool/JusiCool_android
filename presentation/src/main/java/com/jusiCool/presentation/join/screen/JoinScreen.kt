package com.jusiCool.presentation.join.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.button.ButtonState
import com.example.design_system.component.button.JDSButton
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.modifier.padding.paddingHorizontal
import com.example.design_system.component.textfield.JDSTextField
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import kotlinx.coroutines.launch

const val joinRoute = "joinRoute"

// 화면이동을 구현하는 NavController확장함수
fun NavController.navigationToJoin() {
    this.navigate(joinRoute)
}

// navHost에 화면을 등록할 수 있게 하는 확장 함수
fun NavGraphBuilder.joinRoute(
    popUpBackStack: () -> Unit,
    navigateToMain: () -> Unit,
) {
    composable(joinRoute) {
        JoinRoute(
            popUpBackStack = popUpBackStack,
            navigateToMain = navigateToMain
        )
    }
}

// Route
@Composable
fun JoinRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToMain: () -> Unit,
) {
    JoinScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToMain = navigateToMain,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JoinScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToMain: () -> Unit,
) {
    val (authenticationCodeIsSent, setAuthenticationCodeIsSent) = remember { mutableStateOf(false) }
    val (nameTextState, setNameTextState) = remember { mutableStateOf("") }
    val (emailTextState, setEmailTextState) = remember { mutableStateOf("") }
    val (authenticationCodeTextState, setAuthenticationCodeTextState) = remember { mutableStateOf("") }
    val (passWordTextState, setPassWordTextState) = remember { mutableStateOf("") }
    val (rePassWordTextState, setRePassWordTextState) = remember { mutableStateOf("") }
    val coroutine = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(JDSColor.WHITE)
    ) {
        JDSArrowTopBar(
            startIcon = {
                LeftArrowIcon(
                    modifier = Modifier.clickableSingle {
                        if (pagerState.currentPage == 0) popUpBackStack()
                        else coroutine.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                )
            }
        )
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
        ) {
            when (it) {
                0 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .paddingHorizontal(
                                horizontal = 24.dp,
                                top = 2.dp,
                                bottom = 56.dp
                            )
                    ) {
                        Text(
                            text = "이름을 적어 주세요",
                            style = JDSTypography.subTitle,
                            color = JDSColor.Black,
                        )
                        Spacer(modifier = Modifier.fillMaxHeight(0.0552f))
                        JDSTextField(
                            label = "이름",
                            placeHolder = "실명을 적어주세요",
                            textState = nameTextState,
                            onTextChange = setNameTextState,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        JDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(53.dp),
                            text = "다음",
                            state = if (nameTextState.isNotEmpty()) ButtonState.Enable else ButtonState.Disable,
                            onClick = {
                                coroutine.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            },
                        )
                    }
                }

                1 -> {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .paddingHorizontal(
                                horizontal = 24.dp,
                                top = 2.dp,
                                bottom = 56.dp
                            )
                    ) {
                        Text(
                            text = "이메일을 입력해주세요",
                            style = JDSTypography.subTitle,
                            color = JDSColor.Black,
                        )
                        Spacer(modifier = Modifier.fillMaxHeight(0.0552f))
                        Column {
                            JDSTextField(
                                label = "이메일",
                                placeHolder = "이메일을 적어주세요",
                                textState = emailTextState,
                                onTextChange = setEmailTextState,
                            )
                            if (authenticationCodeIsSent) {
                                JDSTextField(
                                    label = "인증번호",
                                    placeHolder = "인증번호를 입력해주세요",
                                    textState = authenticationCodeTextState,
                                    onTextChange = setAuthenticationCodeTextState,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        JDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(53.dp),
                            text = "다음",
                            state = if (nameTextState.isNotEmpty()) ButtonState.Enable else ButtonState.Disable,
                            onClick = {
                                if (!authenticationCodeIsSent) {
                                    setAuthenticationCodeIsSent(true)
                                } else {
                                    coroutine.launch {
                                        pagerState.animateScrollToPage(2)
                                    }
                                }
                            },
                        )
                    }
                }

                2 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .paddingHorizontal(
                                horizontal = 24.dp,
                                top = 2.dp,
                                bottom = 56.dp
                            )
                    ) {
                        Text(
                            text = "비밀번호를 입력해주세요",
                            style = JDSTypography.subTitle,
                            color = JDSColor.Black,
                        )
                        Spacer(modifier = Modifier.fillMaxHeight(0.0552f))
                        Column {
                            JDSTextField(
                                label = "비밀번호",
                                placeHolder = "비밀번호를 입력해주세요",
                                textState = passWordTextState,
                                onTextChange = setPassWordTextState,
                            )
                            JDSTextField(
                                label = "비밀번호 재입력",
                                placeHolder = "비밀번호를 다시 입력해주세요",
                                textState = rePassWordTextState,
                                onTextChange = setRePassWordTextState,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        JDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(53.dp),
                            text = "시작하기",
                            state = if (nameTextState.isNotEmpty()) ButtonState.Enable else ButtonState.Disable,
                            onClick = { if (passWordTextState == rePassWordTextState) navigateToMain() },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun JoinScreenPreview() {
    JoinScreen(
        popUpBackStack = {},
        navigateToMain = {}
    )
}