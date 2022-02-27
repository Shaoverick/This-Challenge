package com.example.passporter.view.repoList

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.RepoEntity
import com.example.domain.model.UrlString
import com.example.passporter.view.repoList.adapter.RepoRVAdapter

@BindingAdapter("addRepoList")
fun addRepoList(recyclerView: RecyclerView?, repoList: MutableList<RepoEntity>?) {
    val adapter = recyclerView?.adapter
    if (adapter is RepoRVAdapter && repoList != null)
        adapter.addData(repoList)
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, urlString: UrlString) {
    val requestOptions = RequestOptions().circleCrop()

    Glide.with(imageView.context)
        .applyDefaultRequestOptions(requestOptions)
        .load(urlString)
        .into(imageView)
}

@BindingAdapter("setOnLongClick")
fun setOnLongClick(
    view: View,
    func: () -> Unit
) {
    view.setOnLongClickListener {
        func()
        return@setOnLongClickListener true
    }
}