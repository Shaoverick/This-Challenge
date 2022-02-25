package com.example.di

import com.example.data.dataSource.remoteDataSource.GitHubRepoRemoteDataSource
import com.example.data.repository.GitHubRepoRepository
import com.example.domain.di.RemoteDataSource
import com.example.domain.di.Repository
import com.example.domain.dataSource.IGitHubRepoDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class GitHubRepoDataSourceModule {

    @RemoteDataSource
    @Singleton
    @Binds
    abstract fun bindGitHubRepoRemoteDataSource(impl: GitHubRepoRemoteDataSource): IGitHubRepoDataSource

    @Repository
    @Singleton
    @Binds
    abstract fun bindGitHubRepoRepository(impl: GitHubRepoRepository): IGitHubRepoDataSource

}
