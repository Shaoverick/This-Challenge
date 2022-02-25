package com.example.passporter.view.repositoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.entity.RepoEntity
import com.example.domain.useCase.gitHubRepo.GetUserRepoListParams
import com.example.domain.useCase.gitHubRepo.GetUserRepoListUC
import com.example.passporter.Event
import com.example.passporter.UIResult
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
    private val _repoList = MutableLiveData<Event<UIResult<List<RepoEntity>>>>()
    val repoList: LiveData<Event<UIResult<List<RepoEntity>>>> = _repoList
    //endregion


    //region PUBLIC METHODS ------------------------------------------------------------------------
    fun getRepoList(page: Int) {
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

            _repoList.postValue(Event(UIResult.fromResult(result)))
        }
    }
    //endregion

}