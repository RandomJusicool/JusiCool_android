package com.jusiCool.jusicool_android.module

import com.jusiCool.data.remote.datesource.auth.RemoteAuthDataSource
import com.jusiCool.data.remote.datesource.auth.RemoteAuthDataSourceImpl
import com.jusiCool.data.remote.datesource.board.RemoteBoardDataSource
import com.jusiCool.data.remote.datesource.board.RemoteBoardDataSourceImpl
import com.jusiCool.data.remote.datesource.comment.RemoteCommentDataSource
import com.jusiCool.data.remote.datesource.comment.RemoteCommentDataSourceImpl
import com.jusiCool.data.remote.datesource.community.RemoteCommunityDataSource
import com.jusiCool.data.remote.datesource.community.RemoteCommunityDataSourceImpl
import com.jusiCool.data.remote.datesource.day.RemoteDayDataSource
import com.jusiCool.data.remote.datesource.day.RemoteDayDataSourceImpl
import com.jusiCool.data.remote.datesource.email.RemoteEmailDataSource
import com.jusiCool.data.remote.datesource.email.RemoteEmailDataSourceImpl
import com.jusiCool.data.remote.datesource.like.RemoteLikeDataSource
import com.jusiCool.data.remote.datesource.like.RemoteLikeDataSourceImpl
import com.jusiCool.data.remote.datesource.reservation.RemoteReservationDataSource
import com.jusiCool.data.remote.datesource.reservation.RemoteReservationDataSourceImpl
import com.jusiCool.data.remote.datesource.stock.RemoteStockDataSource
import com.jusiCool.data.remote.datesource.stock.RemoteStockDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun provideRemoteAuthDataSource(
        remoteAuthDataSourceImpl: RemoteAuthDataSourceImpl
    ) : RemoteAuthDataSource

    @Binds
    abstract fun provideRemoteBoardDataSource(
        remoteBoardDataSourceImpl: RemoteBoardDataSourceImpl
    ) : RemoteBoardDataSource

    @Binds
    abstract fun provideRemoteCommentDataSource(
        remoteCommentDataSourceImpl: RemoteCommentDataSourceImpl
    ) : RemoteCommentDataSource

    @Binds
    abstract fun provideEmailDataSource(
        remoteEmailDataSourceImpl: RemoteEmailDataSourceImpl
    ) : RemoteEmailDataSource

    @Binds
    abstract fun provideReservationDataSource(
        remoteReservationDataSourceImpl: RemoteReservationDataSourceImpl
    ) : RemoteReservationDataSource

    @Binds
    abstract fun provideCommunityDataSource(
        remoteCommunityDataSourceImpl: RemoteCommunityDataSourceImpl
    ) : RemoteCommunityDataSource

    @Binds
    abstract fun provideStockDataSource(
        remoteStockDataSourceImpl: RemoteStockDataSourceImpl
    ) : RemoteStockDataSource

    @Binds
    abstract fun provideLikeDataSource(
        remoteLikeDataSourceImpl: RemoteLikeDataSourceImpl
    ) : RemoteLikeDataSource

    @Binds
    abstract fun provideMyDataSource(
        remoteDayDataSourceImpl: RemoteDayDataSourceImpl
    ) : RemoteDayDataSource
}