package com.jusiCool.jusicool_android.module

import com.jusiCool.data.repository.AuthRepositoryImpl
import com.jusiCool.data.repository.BoardRepositoryImpl
import com.jusiCool.data.repository.CommentRepositoryImpl
import com.jusiCool.data.repository.CommunityRepositoryImpl
import com.jusiCool.data.repository.EmailRepositoryImpl
import com.jusiCool.data.repository.ReservationRepositoryImpl
import com.jusiCool.data.repository.StockRepositoryImpl
import com.jusiCool.data.repository.TokenRepositoryImpl
import com.jusiCool.domain.repository.AuthRepository
import com.jusiCool.domain.repository.BoardRepository
import com.jusiCool.domain.repository.CommentRepository
import com.jusiCool.domain.repository.CommunityRepository
import com.jusiCool.domain.repository.EmailRepository
import com.jusiCool.domain.repository.ReservationRepository
import com.jusiCool.domain.repository.StockRepository
import com.jusiCool.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository

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

    @Binds
    abstract fun provideCommunityRepository(
        communityRepositoryImpl: CommunityRepositoryImpl
    ) : CommunityRepository

    @Binds
    abstract fun provideStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ) : StockRepository

    @Binds
    abstract fun provideTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ) : TokenRepository
}