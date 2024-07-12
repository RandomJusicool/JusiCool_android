package com.jusiCool.jusicool_android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.data.local.datasource.EncryptedSharedPreferencesDataSource
import com.jusiCool.data.utill.isDateExpired
import com.jusiCool.domain.usecase.auth.PatchAuthTokenRefreshUseCase
import com.jusiCool.presentation.login.screen.loginRoute
import com.jusiCool.presentation.main.screen.mainRoute
import com.jusiCool.presentation.utill.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val encryptedSharedPreferencesDataSource: EncryptedSharedPreferencesDataSource,
    private val patchAuthTokenRefreshUseCase: PatchAuthTokenRefreshUseCase,
) : ViewModel() {

    private var _refreshToken: String = encryptedSharedPreferencesDataSource.getRefreshToken() ?: ""
    private var _refreshTokenTime: String =
        encryptedSharedPreferencesDataSource.getRefreshTime() ?: ""

    private var _navigateRoute: String = loginRoute
    val navigateRoute: String
        get() = _navigateRoute

    init {
        checkExpireTime()
    }


    private fun login() = viewModelScope.launch {
        patchAuthTokenRefreshUseCase("Bearer $_refreshToken").onSuccess { result ->
            result.collect { newToken ->
                encryptedSharedPreferencesDataSource.apply {
                    setAccessToken(newToken.accessToken)
                    setAccessTime(newToken.accessTokenExpiresIn)
                    setRefreshToken(newToken.refreshToken)
                    setRefreshTime(newToken.refreshTokenExpiresIn)
                }

                // 새로운 토큰 값으로 상태 업데이트
                _refreshToken = newToken.refreshToken
                _refreshTokenTime = newToken.refreshTokenExpiresIn

                // 네비게이션 경로 업데이트
                _navigateRoute = mainRoute

            }
        }.onFailure { error ->
            Log.d("onFailure",error.message.toString())
        }
    }

    private fun checkExpireTime() {
        if (_refreshTokenTime.isDateExpired()) {
            encryptedSharedPreferencesDataSource.apply {
                deleteRefreshTime()
                deleteRefreshToken()
            }
            _refreshToken = ""
            _refreshTokenTime = ""
        }

        if (_refreshToken.isEmpty()) {
            _navigateRoute = loginRoute
        } else {
            login()
        }
    }

    private fun login() = viewModelScope.launch {
        patchAuthTokenRefreshUseCase("Bearer $_refreshToken")
            .onSuccess { result ->
                result.catch {
                    Log.d("onFailure", it.message.toString())
                }.collect { newToken ->
                    encryptedSharedPreferencesDataSource.apply {
                        setAccessToken(newToken.accessToken)
                        setAccessTime(newToken.accessTokenExpiresIn)
                        setRefreshToken(newToken.refreshToken)
                        setRefreshTime(newToken.refreshTokenExpiresIn)
                    }
                    _refreshToken = newToken.refreshToken
                    _refreshTokenTime = newToken.refreshTokenExpiresIn
                    _navigateRoute = mainRoute
                }
            }
            .onFailure {
                Log.d("onFailure", it.message.toString())
            }
    }
}
