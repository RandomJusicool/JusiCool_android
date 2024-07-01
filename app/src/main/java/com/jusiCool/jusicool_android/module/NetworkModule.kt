package com.jusiCool.jusicool_android.module

import android.util.Log
import com.jusiCool.data.remote.api.BoardAPI
import com.jusiCool.data.remote.api.CommentAPI
import com.jusiCool.data.remote.api.ReservationAPI
import com.jusiCool.data.utill.AuthInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.w3c.dom.Comment
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
    ) = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideMoshiInstance(): Moshi =
        Moshi.Builder().build()


    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("") // Todo : Add BuildConfig
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Provides
    @Singleton
    fun BoardAPI(retrofit: Retrofit): BoardAPI {
        return retrofit.create(BoardAPI::class.java)
    }

    @Provides
    @Singleton
    fun ReservationAPI(retrofit: Retrofit): ReservationAPI {
        return retrofit.create(ReservationAPI::class.java)
    }

    @Provides
    @Singleton
    fun CommentAPI(retrofit: Retrofit): CommentAPI {
        return retrofit.create(CommentAPI::class.java)
    }
}