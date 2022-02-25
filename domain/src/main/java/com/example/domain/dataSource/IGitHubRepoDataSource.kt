package com.example.domain.dataSource

import com.example.domain.model.entity.RepoEntity

interface IGitHubRepoDataSource {

    suspend fun getGitHubRepoList(userName: String, itemsPerPage: Int, page: Int): List<RepoEntity>

}