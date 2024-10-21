package com.c4pn91.mliproject.common.di

import com.c4pn91.mliproject.data.api.ApiClient
import com.c4pn91.mliproject.data.datasource.remote.RemoteProductDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideProductServices(apiClient: ApiClient): RemoteProductDataSource {
        return RemoteProductDataSource(apiClient)
    }
}