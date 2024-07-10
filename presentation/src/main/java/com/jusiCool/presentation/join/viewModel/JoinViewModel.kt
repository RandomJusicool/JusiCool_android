package com.jusiCool.presentation.join.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.auth.request.PostAuthSignUpRequestModel
import com.jusiCool.domain.model.email.request.PostEmailRequestModel
import com.jusiCool.domain.usecase.auth.PostAuthSignUpUseCase
import com.jusiCool.domain.usecase.email.GetEmailVerifyUseCase
import com.jusiCool.domain.usecase.email.PostEmailUseCase
import com.jusiCool.presentation.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val postEmailUseCase: PostEmailUseCase,
    private val getEmailVerifyUseCase: GetEmailVerifyUseCase,
    private val postAuthSignUpUseCase: PostAuthSignUpUseCase,
) : ViewModel() {
    private val _emailSendState = MutableStateFlow<Event<Unit>>(Event.Loading)
    val emailSendState = _emailSendState.asStateFlow()

    private val _emailCheckProcess = MutableStateFlow(value = 0)
    val emailCheckProcess = _emailCheckProcess.asStateFlow()

    fun postEmail(postEmailModel: PostEmailRequestModel) = viewModelScope.launch {
        postEmailUseCase(postEmailModel)
            .onSuccess {
                it.catch {

                    _emailCheckProcess.value = 1
                    _emailSendState.value = Event.Success()

                }.collect {
                    _emailCheckProcess.value = 1
                    _emailSendState.value = Event.Success()
                }
            }
    }

    fun getVerifyEmail(
        email: String,
        authCode: String,
        onSuccess: () -> Unit
    ) =
        viewModelScope.launch {
            getEmailVerifyUseCase(email = email, authCode = authCode)
                .onSuccess {
                    it.catch {
                        _emailCheckProcess.value = 2
                    }.collect {
                        onSuccess()
                        _emailCheckProcess.value = 2
                    }
                }
        }

    fun postSignUp(
        postAuthSignUpModel: PostAuthSignUpRequestModel,
        onSuccess: () -> Unit
    ) = viewModelScope.launch {
        postAuthSignUpUseCase(postAuthSignUpModel)
            .onSuccess {
                it.catch {

                }.collect {
                    onSuccess()
                }
            }
    }
}