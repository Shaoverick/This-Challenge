package com.example.passporter.view.repoList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.entity.RepoEntity
import com.example.domain.model.entity.UrlString
import com.example.passporter.view.repoList.adapter.RepoRVAdapter
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("addRepoList")
fun addRepoList(recyclerView: RecyclerView?, repoList: MutableList<RepoEntity>?) {
    val adapter = recyclerView?.adapter
    if (adapter is RepoRVAdapter && repoList != null)
        adapter.addData(repoList)
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, urlString: UrlString) {
    Glide.with(imageView.context)
        .load(urlString)
        .into(imageView)
}