package com.jusiCool.jusicool_android.module

import com.jusiCool.data.remote.datesource.board.RemoteBoardDataSource
import com.jusiCool.data.remote.datesource.board.RemoteBoardDataSourceImpl
import com.jusiCool.data.remote.datesource.comment.RemoteCommentDataSource
import com.jusiCool.data.remote.datesource.comment.RemoteCommentDataSourceImpl
import com.jusiCool.data.remote.datesource.email.RemoteEmailDataSource
import com.jusiCool.data.remote.datesource.email.RemoteEmailDataSourceImpl
import com.jusiCool.data.remote.datesource.reservation.RemoteReservationDataSource
import com.jusiCool.data.remote.datesource.reservation.RemoteReservationDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
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
}