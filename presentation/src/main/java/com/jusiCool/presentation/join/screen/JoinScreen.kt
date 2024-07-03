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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.jusiCool.domain.model.auth.request.PostAuthSignUpRequestModel
import com.jusiCool.domain.model.email.request.PostEmailRequestModel
import com.jusiCool.presentation.join.viewModel.JoinViewModel
import com.jusiCool.presentation.utill.Event
import kotlinx.coroutines.launch

const val joinRoute = "joinRoute"

// 화면이동을 구현하는 NavController확장함수
fun NavController.navigateToJoin() {
    this.navigate(joinRoute)
}

// navHost에 화면을 등록할 수 있게 하는 확장 함수
fun NavGraphBuilder.joinRoute(
    popUpBackStack: () -> Unit,
) {
    composable(joinRoute) {
        JoinRoute(popUpBackStack = popUpBackStack)
    }
}

// Route
@Composable
fun JoinRoute(
    modifier: Modifier = Modifier,
    joinViewModel: JoinViewModel = hiltViewModel(),
    popUpBackStack: () -> Unit,
) {
    val emailSendState by joinViewModel.emailSendState.collectAsStateWithLifecycle()
    val emailVerifyState by joinViewModel.emailVerifyState.collectAsStateWithLifecycle()
    val signUpState by joinViewModel.signUpState.collectAsStateWithLifecycle()

    JoinScreen(
        modifier = modifier,
        emailSendState = emailSendState,
        emailVerifyState = emailVerifyState,
        signUpState = signUpState,
        postEmail = joinViewModel::postEmail,
        getVerifyEmail = joinViewModel::getVerifyEmail,
        postAuthSignUp = joinViewModel::postSignUp,
        popUpBackStack = popUpBackStack,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JoinScreen(
    modifier: Modifier = Modifier,
    emailSendState: Event<Unit>,
    emailVerifyState: Event<Unit>,
    signUpState: Event<Unit>,
    postEmail: (PostEmailRequestModel) -> Unit,
    getVerifyEmail: (String, String) -> Unit,
    postAuthSignUp: (PostAuthSignUpRequestModel) -> Unit,
    popUpBackStack: () -> Unit,
) {
    val (nameTextState, setNameTextState) = remember { mutableStateOf("") }
    val (emailTextState, setEmailTextState) = remember { mutableStateOf("") }
    val (authenticationCodeTextState, setAuthenticationCodeTextState) = remember { mutableStateOf("") }
    val (passWordTextState, setPassWordTextState) = remember { mutableStateOf("") }
    val (rePassWordTextState, setRePassWordTextState) = remember { mutableStateOf("") }
    val coroutine = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }

    LaunchedEffect(signUpState is Event.Success) {
        popUpBackStack()
    }

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
            userScrollEnabled = false,
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
                            state = if (nameTextState.isNotEmpty()) ButtonState.Enable
                            else ButtonState.Disable,
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
                                isEnabled = emailSendState is Event.Loading,
                                textState = emailTextState,
                                onTextChange = setEmailTextState,
                            )
                            if (emailSendState is Event.Success) {
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
                            text = if (emailSendState !is Event.Success) "코드 받기"
                            else if (emailVerifyState !is Event.Success) "확인"
                            else "다음",
                            state = if (nameTextState.isNotEmpty()) ButtonState.Enable
                            else ButtonState.Disable,
                            onClick = {
                                if (emailSendState !is Event.Success) postEmail(
                                    PostEmailRequestModel(email = emailTextState)
                                )
                                else if (emailVerifyState !is Event.Success)
                                    getVerifyEmail(
                                        emailTextState,
                                        authenticationCodeTextState
                                    )
                                else coroutine.launch {
                                    pagerState.animateScrollToPage(2)
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
                            state = if (nameTextState.isNotEmpty()) ButtonState.Enable
                            else ButtonState.Disable,
                            onClick = {
                                if (passWordTextState == rePassWordTextState) {
                                    postAuthSignUp(
                                        PostAuthSignUpRequestModel(
                                            email = emailTextState,
                                            name = nameTextState,
                                            password = passWordTextState,
                                        )
                                    )
                                }
                            },
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
        emailSendState = Event.Success(),
        emailVerifyState = Event.Success(),
        signUpState = Event.Success(),
        popUpBackStack = { },
        postEmail = { },
        getVerifyEmail = { _, _ -> },
        postAuthSignUp = { },
    )
}