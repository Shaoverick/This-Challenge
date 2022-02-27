package com.example.passporter.view.repoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.entity.OwnerEntity
import com.example.domain.model.entity.RepoEntity
import com.example.domain.useCase.gitHubRepo.GetUserRepoListParams
import com.example.domain.useCase.gitHubRepo.GetUserRepoListUC
import com.example.passporter.Event
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
        //This properties should not be stored here
        private const val USER_NAME = "shaoverick"
        private const val ITEMS_PER_PAGE = 10
    }


    //region PROPERTIES ----------------------------------------------------------------------------
    private val _repoList = MutableLiveData<Event<List<RepoEntity>>>()
    val repoList: LiveData<Event<List<RepoEntity>>> = _repoList

    private val _apiCallError = MutableLiveData<Event<String>>()
    val apiCallError: LiveData<Event<String>> = _apiCallError

    private var page = 0
    //endregion


    init {
        getRepoListPage()
    }


    //region PUBLIC METHODS ------------------------------------------------------------------------
    fun getRepoListPage() {
        page++

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getUserRepoListUC.execute(
                    GetUserRepoListParams(
                        userName = USER_NAME,
                        itemsPerPage = ITEMS_PER_PAGE,
                        page = page
                    )
                )
            }

            result.peekSuccessOrNull()?.let {
                _repoList.postValue(Event(it))
            }

            result.peekFailureOrNull()?.let {
                _apiCallError.postValue(Event(it.message ?: "Network error"))
            }

//            _repoList.postValue(Event(mock()))
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
                avatarUrl = "https://avatars.githubusercontent.com/u/25582459?s=400&u=fb0b79769f9ba6ff8e01cf4c898e05e2b6ff2a7d&v=4",
                htmlUrl = "http://www.google.com",
            ),
            fork = true,
            htmlUrl = "http://www.google.com"
        ))
    }

}