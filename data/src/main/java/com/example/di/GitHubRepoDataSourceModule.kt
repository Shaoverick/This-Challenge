package com.example.di

import com.example.data.dataSource.remoteDataSource.GitHubRepoRemoteDataSource
import com.example.domain.dataSource.IGitHubRepoDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class GitHubRepoDataSourceModule {

//    @Qualifier
//    annotation class RemoteDataSource

    @Singleton
    @Binds
    abstract fun bindGitHubRepoDataSourceModule(impl: GitHubRepoRemoteDataSource): IGitHubRepoDataSource

}
