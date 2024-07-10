package com.jusiCool.presentation.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.auth.request.PostAuthSignInRequestModel
import com.jusiCool.domain.repository.TokenRepository
import com.jusiCool.domain.usecase.auth.PostAuthSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postAuthSignInUseCase: PostAuthSignInUseCase,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    fun loginFunc(
        signInModel: PostAuthSignInRequestModel,
        onSuccess: () -> Unit, // 성공 시 실행할 콜백 함수
    ) = viewModelScope.launch {
        postAuthSignInUseCase(signInModel)
            .onSuccess {
                it.catch {

                }.collect { authTokenResponseModel ->
                    onSuccess()
                    tokenRepository.apply {
                        setAccessTime(authTokenResponseModel.accessTokenExpiresIn)
                        setAccessToken(authTokenResponseModel.accessToken)
                        setRefreshTime(authTokenResponseModel.refreshTokenExpiresIn)
                        setRefreshToken(authTokenResponseModel.refreshToken)
                    }
                }
            }
            .onFailure {
            }
    }
}
