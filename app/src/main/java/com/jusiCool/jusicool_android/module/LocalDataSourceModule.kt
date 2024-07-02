package com.jusiCool.jusicool_android.module

import com.jusiCool.data.local.datasource.AuthTokenDataSource
import com.jusiCool.data.local.datasource.AuthTokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindAuthTokenDataSource(
        authTokenDataSourceImpl: AuthTokenDataSourceImpl
    ) : AuthTokenDataSource
}