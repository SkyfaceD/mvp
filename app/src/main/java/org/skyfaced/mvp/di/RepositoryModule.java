package org.skyfaced.mvp.di;

import org.skyfaced.mvp.service.network.WaifuRepository;
import org.skyfaced.mvp.service.network.WaifuRepositoryImpl;
import org.skyfaced.mvp.service.network.WaifuService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public final class RepositoryModule {
    @Provides
    @Singleton
    WaifuRepository waifuRepository(WaifuService waifuService) {
        return new WaifuRepositoryImpl(waifuService);
    }
}
