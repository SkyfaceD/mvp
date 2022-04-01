package org.skyfaced.mvp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.skyfaced.mvp.service.network.WaifuRepository
import org.skyfaced.mvp.service.network.WaifuRepositoryImpl
import org.skyfaced.mvp.service.network.WaifuService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun waifuRepository(
        waifuService: WaifuService,
    ): WaifuRepository = WaifuRepositoryImpl(waifuService)
}