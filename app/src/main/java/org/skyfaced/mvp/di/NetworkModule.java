package org.skyfaced.mvp.di;

import org.skyfaced.mvp.service.network.AppNetwork;
import org.skyfaced.mvp.service.network.JikanService;
import org.skyfaced.mvp.service.network.WaifuService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public final class NetworkModule {
    @Provides
    @Singleton
    WaifuService provideWaifuService() {
        return AppNetwork.getInstance().waifuService;
    }

    @Provides
    @Singleton
    JikanService provideJikanService() {
        return AppNetwork.getInstance().jikanService;
    }
}
