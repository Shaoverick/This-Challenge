package com.example.data.repository

import com.example.domain.di.RemoteDataSource
import com.example.domain.dataSource.IGitHubRepoDataSource
import com.example.domain.model.RepoEntity
import javax.inject.Inject

//Currently, there is no hard reason to have a Repository, because the app has not data persistence in any local data base.
//This class should be fully implemented when app had local database persistence
class GitHubRepoRepository @Inject constructor() : IGitHubRepoDataSource {

    //region PROPERTIES ----------------------------------------------------------------------------
    @RemoteDataSource
    @Inject lateinit var gitHubRepoRemoteDataSource: IGitHubRepoDataSource
    //endregion


    //region PUBLIC METHODS ------------------------------------------------------------------------
    override suspend fun getGitHubRepoList(userName: String, itemsPerPage: Int, page: Int): List<RepoEntity> {
        return gitHubRepoRemoteDataSource.getGitHubRepoList(
            userName = userName,
            itemsPerPage = itemsPerPage,
            page = page
        )
    }
    //endregion

}