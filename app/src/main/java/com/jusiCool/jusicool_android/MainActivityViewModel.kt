package com.jusiCool.jusicool_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jusiCool.data.local.datasource.EncryptedSharedPreferencesDataSource
import com.jusiCool.data.utill.isDateExpired
import com.jusiCool.domain.usecase.auth.PatchAuthTokenRefreshUseCase
import com.jusiCool.presentation.login.screen.loginRoute
import com.jusiCool.presentation.main.screen.mainRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val encryptedSharedPreferencesDataSource: EncryptedSharedPreferencesDataSource,
    private val patchAuthTokenRefreshUseCase: PatchAuthTokenRefreshUseCase,
) : ViewModel() {

    // 단순한 변수로 상태 관리
    private var _accessToken: String = ""
    val accessToken: String
        get() = _accessToken

    private var _accessTokenTime: String = ""
    val accessTokenTime: String
        get() = _accessTokenTime

    private var _refreshToken: String = ""
    val refreshToken: String
        get() = _refreshToken

    private var _refreshTokenTime: String = ""
    val refreshTokenTime: String
        get() = _refreshTokenTime

    private var _navigateRoute: String = loginRoute
    val navigateRoute: String
        get() = _navigateRoute

    init {
        _accessToken = encryptedSharedPreferencesDataSource.getAccessToken() ?: ""
        _accessTokenTime = encryptedSharedPreferencesDataSource.getAccessTime() ?: ""
        _refreshToken = encryptedSharedPreferencesDataSource.getRefreshToken() ?: ""
        _refreshTokenTime = encryptedSharedPreferencesDataSource.getRefreshTime() ?: ""
        checkExpireTime()
        login()
    }

    fun login() = viewModelScope.launch {
        val refreshToken = encryptedSharedPreferencesDataSource.getRefreshToken()
        if (refreshToken != null) {
            patchAuthTokenRefreshUseCase().onSuccess { result ->
                result.collect { newToken ->
                    encryptedSharedPreferencesDataSource.setAccessToken(newToken.accessToken)
                    encryptedSharedPreferencesDataSource.setAccessTime(newToken.accessTokenExpiresIn)
                    encryptedSharedPreferencesDataSource.setRefreshToken(newToken.refreshToken)
                    encryptedSharedPreferencesDataSource.setRefreshTime(newToken.refreshTokenExpiresIn)

                    // 새로운 토큰 값으로 상태 업데이트
                    _accessToken = newToken.accessToken
                    _accessTokenTime = newToken.accessTokenExpiresIn
                    _refreshToken = newToken.refreshToken
                    _refreshTokenTime = newToken.refreshTokenExpiresIn

                    // 네비게이션 경로 업데이트
                    _navigateRoute = mainRoute
                }
            }.onFailure {

            }
        } else {
            _navigateRoute = loginRoute
        }
    }

    private fun checkExpireTime() {
        // 액세스 토큰 만료 여부 확인 및 삭제
        if (_accessTokenTime.isDateExpired()) {
            encryptedSharedPreferencesDataSource.deleteAccessTime()
            encryptedSharedPreferencesDataSource.deleteAccessToken()
            _accessToken = ""
            _accessTokenTime = ""
        }

        // 리프레시 토큰 만료 여부 확인 및 삭제
        if (_refreshTokenTime.isDateExpired()) {
            encryptedSharedPreferencesDataSource.deleteRefreshTime()
            encryptedSharedPreferencesDataSource.deleteRefreshToken()
            _refreshToken = ""
            _refreshTokenTime = ""
        }

        // 토큰 유효성에 따라 네비게이션 경로 업데이트
        _navigateRoute = if (_accessToken.isNotEmpty() && !_accessTokenTime.isDateExpired()) {
            mainRoute
        } else {
            loginRoute
        }
    }
}
