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
    private val _emailVerifyState = MutableStateFlow<Event<Unit>>(Event.Loading)
    val emailVerifyState = _emailVerifyState.asStateFlow()

    private val _emailSendState = MutableStateFlow<Event<Unit>>(Event.Loading)
    val emailSendState = _emailSendState.asStateFlow()

    private val _signUpState = MutableStateFlow<Event<Unit>>(Event.Loading)
    val signUpState = _signUpState.asStateFlow()

    fun postEmail(postEmailModel: PostEmailRequestModel) = viewModelScope.launch {
        postEmailUseCase(postEmailModel)
            .onSuccess { _emailSendState.value = Event.Success() }
    }

    fun getVerifyEmail(email: String, authCode: String) = viewModelScope.launch {
        getEmailVerifyUseCase(email = email, authCode = authCode)
            .onSuccess { _emailVerifyState.value = Event.Success() }
    }

    fun postSignUp(postAuthSignUpModel: PostAuthSignUpRequestModel) = viewModelScope.launch {
        postAuthSignUpUseCase(postAuthSignUpModel)
            .onSuccess { _signUpState.value = Event.Success() }
    }
}