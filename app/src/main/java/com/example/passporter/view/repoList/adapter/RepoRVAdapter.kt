package com.example.passporter.view.repoList.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.entity.RepoEntity
import com.example.passporter.R
import com.example.passporter.databinding.RvItemRepoBinding

class RepoRVAdapter(
    private val repoList: MutableList<RepoEntity>,
    private val repoListener: RepoListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //region RECYCLER METHODS ----------------------------------------------------------------------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemRepoBinding.inflate(inflater, parent, false)
        return RepoHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? RepoHolder)?.bind(repoList[position]) ?: throw Resources.NotFoundException()
    }

    override fun getItemCount(): Int {
        return repoList.count()
    }
    //endregion


    //region PUBLIC METHODS ------------------------------------------------------------------------
    fun addData(repoList: MutableList<RepoEntity>) {
        if (repoList.isNotEmpty()) {
            val oldCount = repoList.count()
            this.repoList.addAll(repoList)
            val newCount = repoList.count()
            notifyItemRangeChanged(oldCount, newCount)
        }
    }
    //endregion


    //region HOLDERS -------------------------------------------------------------------------------
    inner class RepoHolder(private val binding: RvItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repoEntity: RepoEntity) {
            binding.repoEntity = repoEntity
            binding.listener = repoListener

            val colorResourceId = if (repoEntity.fork) R.color.card_light_green else R.color.white
            binding.cardRepo.setCardBackgroundColor(binding.root.context.resources.getColor(colorResourceId))
        }
    }
    //endregion


    interface RepoListener {
        fun onLongClick(repoEntity: RepoEntity)
    }

}