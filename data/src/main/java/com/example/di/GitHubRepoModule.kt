package com.example.di

import com.example.client.RemoteClient
import com.example.data.dataSource.remoteDataSource.GitHubRepoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GitHubRepoModule {

    @Provides
    @Singleton
    fun provideGitHubRepoService(): GitHubRepoService {
        return RemoteClient().createService(GitHubRepoService::class.java)
    }

}
