package com.jusiCool.jusicool_android.module

import com.jusiCool.data.remote.datesource.reservation.RemoteReservationDataSourceImpl
import com.jusiCool.data.repository.BoardRepositoryImpl
import com.jusiCool.data.repository.CommentRepositoryImpl
import com.jusiCool.data.repository.EmailRepositoryImpl
import com.jusiCool.data.repository.ReservationRepositoryImpl
import com.jusiCool.domain.repository.BoardRepository
import com.jusiCool.domain.repository.CommentRepository
import com.jusiCool.domain.repository.EmailRepository
import com.jusiCool.domain.repository.ReservationRepository
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideBoardRepository(
        boardRepositoryImpl: BoardRepositoryImpl
    ) : BoardRepository

    @Binds
    abstract fun provideCommentRepository(
        commentRepositoryImpl: CommentRepositoryImpl
    ) : CommentRepository

    @Binds
    abstract fun provideEmailRepository(
        emailRepositoryImpl: EmailRepositoryImpl
    ) : EmailRepository

    @Binds
    abstract fun provideReservationRepository(
        reservationRepositoryImpl: ReservationRepositoryImpl
    ) : ReservationRepository
}