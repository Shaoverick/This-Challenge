package com.example.passporter.view.repoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.entity.RepoEntity
import com.example.passporter.databinding.FragmentRepoListBinding
import com.example.passporter.view.repoList.adapter.RepoRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : Fragment() {

    //region PROPERTIES ----------------------------------------------------------------------------
    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RepoListVM by viewModels()
    //endregion


    //region LIFECYCLE ----------------------------------------------------------------------------
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)

        //Binding fragment to xml
        binding.repoListVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion


    //region EVENTS -----------------------------------------------------------------------------
    private val adapterListener = object : RepoRVAdapter.RepoListener {
        override fun onLongClick(repoEntity: RepoEntity) {
            Toast.makeText(requireContext() ,"Clicked", Toast.LENGTH_LONG).show()
        }
    }

    private val onRepoRVScrollBottom = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!recyclerView.canScrollVertically(1))
                viewModel.getRepoListPage()
        }
    }
    //endregion


    //region PRIVATE METHODS -----------------------------------------------------------------------
    private fun setLayout() {
        binding.rvRepoList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RepoRVAdapter(mutableListOf(), adapterListener)
            addOnScrollListener(onRepoRVScrollBottom)
        }
    }
    //endregion

}