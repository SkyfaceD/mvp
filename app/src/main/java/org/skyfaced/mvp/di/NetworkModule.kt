package org.skyfaced.mvp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.skyfaced.mvp.service.network.AppNetwork
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideWaifuService() = AppNetwork.waifuService

    @Provides
    @Singleton
    fun provideJikanService() = AppNetwork.jikanService
}
