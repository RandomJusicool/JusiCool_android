package com.jusiCool.data.local.datasource

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class EncryptedSharedPreferencesDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val tokenAdapter: JsonAdapter<String>
) : EncryptedSharedPreferencesDataSource {
    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val ACCESS_TIME = "access_time"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val REFRESH_TIME = "access_time"
    }

    private val editor = sharedPreferences.edit()

    override fun getAccessToken(): String? {
        val json = sharedPreferences.getString(ACCESS_TOKEN, null)
        return if (json != null) {
            tokenAdapter.fromJson(json)
        } else {
            null
        }
    }

    override fun setAccessToken(accessToken: String) {
        val json = tokenAdapter.toJson(accessToken)
        editor.putString(ACCESS_TOKEN, json)
        editor.apply()
    }

    override fun deleteAccessToken() {
        sharedPreferences.edit().remove(ACCESS_TOKEN).apply()
    }

    override fun getAccessTime(): String? {
        val json = sharedPreferences.getString(ACCESS_TIME, null)
        return if (json != null) {
            tokenAdapter.fromJson(json)
        } else {
            null
        }
    }

    override fun setAccessTime(accessTime: String) {
        val json = tokenAdapter.toJson(accessTime)
        editor.putString(ACCESS_TIME, json)
        editor.apply()
    }

    override fun deleteAccessTime() {
        sharedPreferences.edit().remove(ACCESS_TIME).apply()
    }

    override fun getRefreshToken(): String? {
        val json = sharedPreferences.getString(REFRESH_TOKEN, null)
        return if (json != null) {
            tokenAdapter.fromJson(json)
        } else {
            null
        }
    }

    override fun setRefreshToken(refreshToken: String) {
        val json = tokenAdapter.toJson(refreshToken)
        editor.putString(REFRESH_TOKEN, json)
        editor.apply()
    }

    override fun deleteRefreshToken() {
        sharedPreferences.edit().remove(REFRESH_TOKEN).apply()
    }

    override fun getRefreshTime(): String? {
        val json = sharedPreferences.getString(REFRESH_TIME, null)
        return if (json != null) {
            tokenAdapter.fromJson(json)
        } else {
            null
        }
    }

    override fun setRefreshTime(refreshTime: String) {
        val json = tokenAdapter.toJson(refreshTime)
        editor.putString(REFRESH_TIME, json)
        editor.apply()    }

    override fun deleteRefreshTime() {
        sharedPreferences.edit().remove(REFRESH_TIME).apply()
    }
}