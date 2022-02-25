package com.example.di

import com.example.data.dataSource.remoteDataSource.GitHubRepoRemoteDataSource
import com.example.domain.model.entity.RepoEntity
import com.example.domain.useCase.UseCase
import com.example.domain.useCase.gitHubRepo.GetUserRepoListParams
import com.example.domain.useCase.gitHubRepo.GetUserRepoListUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesGitHubRepoUCModule(
        gitHubRepoDataSource: GitHubRepoRemoteDataSource
    ): UseCase<GetUserRepoListParams, List<RepoEntity>> {
        return GetUserRepoListUC(gitHubRepoDataSource)
    }

}
