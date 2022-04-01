package org.skyfaced.mvp.di;

import org.skyfaced.mvp.service.instant.InstantService;
import org.skyfaced.mvp.service.network.WaifuRepository;
import org.skyfaced.mvp.ui.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public final class PresenterModule {
    @Provides
    @Singleton
    MainPresenter mainPresenter(WaifuRepository waifuRepository, InstantService instantService) {
        return new MainPresenter(waifuRepository, instantService);
    }
}
