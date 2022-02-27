package com.example.data.dataSource.remoteDataSource

import com.example.client.NetworkDeferred
import com.example.apiModel.RepoApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubRepoService {

    companion object {
        private const val USERS_ENDPOINT_PATH = "users"
        private const val REPOS = "repos"

        private const val USER_NAME = "userName"

        //Example: https://api.github.com/users/shaoverick/repos?per_page=5&page=3
        const val PATH__GET_REPO_LIST = "$USERS_ENDPOINT_PATH/{$USER_NAME}/$REPOS"

        const val PATH_PARAM__ITEMS_PER_PAGE = "per_page"
        const val PATH_PARAM__PAGE = "page"
    }


    @GET(PATH__GET_REPO_LIST)
    suspend fun getRepoList(
        @Path(USER_NAME) userName: String,
        @Query(PATH_PARAM__ITEMS_PER_PAGE) itemsPerPage: Int,
        @Query(PATH_PARAM__PAGE) page: Int,
    ): NetworkDeferred<List<RepoApiModel>>

}
