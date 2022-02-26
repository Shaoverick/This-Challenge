package com.example.passporter.view.repoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.entity.OwnerEntity
import com.example.domain.model.entity.RepoEntity
import com.example.domain.useCase.gitHubRepo.GetUserRepoListParams
import com.example.domain.useCase.gitHubRepo.GetUserRepoListUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RepoListVM @Inject constructor(
    var getUserRepoListUC: GetUserRepoListUC
) : ViewModel() {

    companion object {
        private const val USER_NAME = "shaoverick"
        private const val ITEMS_PER_PAGE = 10
    }


    //region PROPERTIES ----------------------------------------------------------------------------
    private val _repoList = MutableLiveData<List<RepoEntity>>()
    val repoList: LiveData<List<RepoEntity>> = _repoList

    private var page = 0
    //endregion


    init {
        getRepoListPage()
    }


    //region PUBLIC METHODS ------------------------------------------------------------------------
    fun getRepoListPage() {
        page++

        viewModelScope.launch {
            val result: List<RepoEntity> = withContext(Dispatchers.IO) {
                getUserRepoListUC.execute(
                    GetUserRepoListParams(
                        userName = USER_NAME,
                        itemsPerPage = ITEMS_PER_PAGE,
                        page = page
                    )
                )
            }.peekSuccessOrNull() ?: arrayListOf()

            _repoList.postValue(result)
//            _repoList.postValue(mock())
        }
    }
    //endregion


    //For fast mocking purposes
    private fun mock(): List<RepoEntity> {
        return listOf(RepoEntity(
            name = "nombre del repo",
            description = "descripción del repo",
            ownerEntity = OwnerEntity(
                login = "Míguel",
                avatarUrl = null
            ),
            fork = true
        ))
    }

}