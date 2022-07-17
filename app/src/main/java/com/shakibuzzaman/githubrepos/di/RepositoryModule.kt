package com.shakibuzzaman.githubrepos.di

import com.shakibuzzaman.githubrepos.api.RepositoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepositoryApi(retrofit:Retrofit): RepositoryApi = retrofit.create(RepositoryApi::class.java)

}