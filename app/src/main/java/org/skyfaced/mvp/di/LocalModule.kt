package org.skyfaced.mvp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.skyfaced.mvp.service.instant.InstantService
import org.skyfaced.mvp.service.instant.InstantServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideInstanceService(): InstantService = InstantServiceImpl()
}