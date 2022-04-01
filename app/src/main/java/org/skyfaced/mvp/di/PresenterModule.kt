package org.skyfaced.mvp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.skyfaced.mvp.service.instant.InstantService
import org.skyfaced.mvp.service.network.WaifuRepository
import org.skyfaced.mvp.ui.MainPresenter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresenterModule {
    @Provides
    @Singleton
    fun mainPresenter(
        waifuRepository: WaifuRepository,
        instantService: InstantService,
    ) = MainPresenter(waifuRepository, instantService)
}