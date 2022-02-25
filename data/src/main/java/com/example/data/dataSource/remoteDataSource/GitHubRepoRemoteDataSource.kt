package com.example.data.dataSource.remoteDataSource

import com.example.client.awaitToResponse
import com.example.data.mapper.GitHubRepoMapper
import com.example.domain.dataSource.IGitHubRepoDataSource
import com.example.domain.model.entity.RepoEntity
import javax.inject.Inject

//This data source is used to get data from GitHub Api through Retrofit client
class GitHubRepoRemoteDataSource @Inject constructor(
    var service: GitHubRepoService,
    var mapper: GitHubRepoMapper
) : IGitHubRepoDataSource {

    //region PUBLIC METHODS ------------------------------------------------------------------------
    override suspend fun getGitHubRepoList(userName: String, itemsPerPage: Int, page: Int): List<RepoEntity> {
        val repoList = awaitToResponse {
            service.getRepoList(
                userName = userName,
                itemsPerPage = itemsPerPage,
                page = page
            )
        }

        return mapper.toEntityList(repoList)
    }
    //endregion

}