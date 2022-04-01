package org.skyfaced.mvp.di;

import org.skyfaced.mvp.service.instant.InstantService;
import org.skyfaced.mvp.service.instant.InstantServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public final class LocalModule {
    @Provides
    @Singleton
    InstantService provideInstanceService() {
        return new InstantServiceImpl();
    }
}
