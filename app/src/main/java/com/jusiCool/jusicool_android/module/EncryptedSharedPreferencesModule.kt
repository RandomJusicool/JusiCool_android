package com.jusiCool.jusicool_android.module

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.jusiCool.data.local.datasource.EncryptedSharedPreferencesDataSource
import com.jusiCool.data.local.datasource.EncryptedSharedPreferencesDataSourceImpl
import com.squareup.moshi.JsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EncryptedSharedPreferencesModule {
    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // 키 암호화 스키마
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // 값 암호화 스키마
        )
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferencesDataSource(
        sharedPreferences: SharedPreferences,
        tokenAdapter: JsonAdapter<String>
    ): EncryptedSharedPreferencesDataSource {
        return EncryptedSharedPreferencesDataSourceImpl(sharedPreferences, tokenAdapter)
    }
}