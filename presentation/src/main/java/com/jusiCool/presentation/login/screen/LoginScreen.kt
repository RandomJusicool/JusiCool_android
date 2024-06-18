package com.jusiCool.presentation.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.design_system.component.textfield.JDSTextField
import com.example.design_system.icon_image.image.LogoImage
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.presentation.utill.checkEmailRegex
import com.jusiCool.presentation.utill.checkPasswordRegex

const val loginRoute = "loginRoute"


fun NavController.navigationToLogin() {
   this.navigate(loginRoute)
}

fun NavGraphBuilder.loginRoute(
   signUpClicked: () -> Unit,
   loginClicked: () -> Unit,
   findPassword: () -> Unit
) {
   composable(route = loginRoute) {
      LoginRoute(
         signUpClicked = signUpClicked,
         loginClicked = loginClicked,
         findPassword = findPassword
      )
   }
}

@Composable
internal fun LoginRoute(
   modifier: Modifier = Modifier,
   signUpClicked: () -> Unit,
   loginClicked: () -> Unit, // 디자인 적용후 사용예정
   findPassword: () -> Unit,
) {
   val focusManager = LocalFocusManager.current
   
   LoginScreen(
      modifier = modifier,
      focusManager = focusManager,
      signUpClicked = signUpClicked,
      loginClicked = { email, password -> }, // 추후 viewmodel 개발후 통신 예정
      findPassword = findPassword,
   )
}

@Composable
internal fun LoginScreen(
   modifier: Modifier = Modifier,
   focusManager: FocusManager,
   signUpClicked: () -> Unit,
   loginClicked: (String, String) -> Unit = { _ ,_ -> },
   findPassword: () -> Unit,
) {
   CompositionLocalProvider(LocalFocusManager provides focusManager) {
      JusiCoolAndroidTheme { colors, typography ->
         val emailTextState = remember { mutableStateOf("") }
         val passwordTextState = remember { mutableStateOf("") }
         var isTextStatus = ""

         Column(
            modifier = modifier
               .fillMaxSize()
               .background(colors.WHITE)
               .pointerInput(Unit) {
                  detectTapGestures {
                     focusManager.clearFocus()
                  }
               }
         ) {
            LogoImage(modifier = modifier.padding(start = 24.dp, top = 60.dp))
            Spacer(modifier = Modifier.height(6.dp))
            Text(
               modifier = Modifier.padding(start = 26.dp),
               text = "JusiCool로 간단하게 모의투자부터",
               style = typography.bodySmall,
               color = colors.GRAY600
            )
            Spacer(modifier = Modifier.padding(top = 58.dp))
            JDSTextField(
               modifier = Modifier.padding(horizontal = 24.dp),
               label = "이메일",
               textFieldInfo = "아이디를 입력해주세요",
               textState = "",
               textFieldOutlineColor = colors.GRAY100,
               onTextChange = {}
            )
            Spacer(modifier = Modifier.height(4.dp))
            JDSTextField(
               modifier = Modifier.padding(horizontal = 24.dp),
               label = "비밀번호",
               textFieldInfo = "비빌번호를 입력해주세요",
               textState = "",
               textFieldOutlineColor = colors.GRAY100,
               onTextChange = {}
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
               modifier = Modifier
                  .paddingHorizontal(horizontal = 24.dp, bottom = 82.dp)
            ) {
               JDSButton(
                  modifier = Modifier
                     .fillMaxWidth()
                     .height(54.dp),
                  text = "확인",
                  state = if (emailTextState.value.checkEmailRegex() && passwordTextState.value.checkPasswordRegex()) ButtonState.Enable else ButtonState.Disable,
                  onClick = { loginClicked(emailTextState.value, passwordTextState.value) }
               )
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                  modifier = Modifier.fillMaxWidth(),
                  text = "아직 계정이 없으신가요?",
                  style = typography.Regular,
                  color = colors.GRAY1,
               )
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                  modifier = Modifier
                     .fillMaxWidth()
                     .clickableSingle {
                        signUpClicked()
                     },
                  text = "회원가입",
                  style = typography.RegularM,
                  color = colors.MAIN,
               )
            }
         }
      }
   }
}

@Preview
@Composable
private fun LoginScreenPre() {
   LoginRoute(
      signUpClicked = { /*TODO*/ },
      loginClicked = { /*TODO*/ }
   ) {}
}