package com.example.passporter.view.repoList

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.entity.RepoEntity
import com.example.passporter.view.repoList.adapter.RepoRVAdapter

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RepoRVAdapter?) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@BindingAdapter("addRepoList")
fun addRepoList(recyclerView: RecyclerView?, repoList: MutableList<RepoEntity>?) {
    val adapter = recyclerView?.adapter
    if (adapter is RepoRVAdapter && repoList != null)
        adapter.addData(repoList)
}