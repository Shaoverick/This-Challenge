package com.example.domain.useCase.gitHubRepo

import com.example.domain.di.Repository
import com.example.domain.dataSource.IGitHubRepoDataSource
import com.example.domain.model.RepoEntity
import com.example.domain.useCase.UseCase
import javax.inject.Inject

//Use case to get paginated GitHub repo list of the chosen user defined by userName parameter
class GetUserRepoListUC @Inject constructor(): UseCase<GetUserRepoListParams, List<RepoEntity>>() {

    //region PROPERTIES ----------------------------------------------------------------------------
    @Repository
    @Inject lateinit var gitHubRepoRepository: IGitHubRepoDataSource
    //endregion


    //region PUBLIC METHODS ------------------------------------------------------------------------
    override suspend fun call(params: GetUserRepoListParams): List<RepoEntity> {
        return gitHubRepoRepository.getGitHubRepoList(
            userName = params.userName,
            itemsPerPage = params.itemsPerPage,
            page = params.page
        )
    }
    //endregion

}


data class GetUserRepoListParams(
    val userName: String,
    val itemsPerPage: Int,
    val page: Int
)