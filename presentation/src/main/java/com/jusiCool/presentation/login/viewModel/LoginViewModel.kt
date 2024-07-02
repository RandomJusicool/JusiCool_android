package com.jusiCool.presentation.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.domain.model.auth.request.PostAuthSignInRequestModel
import com.jusiCool.domain.usecase.auth.PostAuthSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postAuthSignInUseCase: PostAuthSignInUseCase,
) : ViewModel() {
    fun loginFunc(signInModel: PostAuthSignInRequestModel) =
        viewModelScope.launch {
            postAuthSignInUseCase(signInModel)
                .onSuccess {

                }
                .onFailure {

                }
        }
}