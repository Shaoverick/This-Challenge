package com.example.domain.useCase.gitHubRepo

import com.example.domain.dataSource.IGitHubRepoDataSource
import com.example.domain.model.entity.RepoEntity
import com.example.domain.useCase.UseCase
import javax.inject.Inject

class GetUserRepoListUC @Inject constructor(
    var gitHubRepoDataSource: IGitHubRepoDataSource
): UseCase<GetUserRepoListParams, List<RepoEntity>>() {

    override suspend fun call(params: GetUserRepoListParams): List<RepoEntity> {
        return gitHubRepoDataSource.getGitHubRepoList(
            userName = params.userName,
            itemsPerPage = params.itemsPerPage,
            page = params.page
        )
    }

}


data class GetUserRepoListParams(
    val userName: String,
    val itemsPerPage: Int,
    val page: Int
)