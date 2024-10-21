package com.c4pn91.mliproject.common.di

import com.c4pn91.mliproject.data.repository.DetailsRepositoryImpl
import com.c4pn91.mliproject.data.repository.ProductRepositoryImpl
import com.c4pn91.mliproject.data.datasource.remote.RemoteProductDataSource
import com.c4pn91.mliproject.domain.repository.DetailsRepository
import com.c4pn91.mliproject.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(remoteProductDataSource: RemoteProductDataSource): ProductsRepository {
        return ProductRepositoryImpl(remoteProductDataSource)
    }

    @Provides
    @Singleton
    fun provideDetailsRepository(remoteProductDataSource: RemoteProductDataSource): DetailsRepository {
        return DetailsRepositoryImpl(remoteProductDataSource)
    }
}