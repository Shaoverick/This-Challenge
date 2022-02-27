package com.example.passporter.view.repoList

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.RepoEntity
import com.example.domain.model.UrlString
import com.example.passporter.EventObserver
import com.example.passporter.databinding.FragmentRepoListBinding
import com.example.passporter.view.repoList.adapter.RepoRVAdapter
import com.example.passporter.view.repoList.dialog.WebChooserDialog
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
        binding.apply {
            repoListVM = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setApiCallErrorObserver()
        setDonutProgressObserver()

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
            showWebChooserDialog(repoEntity)
        }
    }

    private val onRepoRVScrollBottom = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING)
                viewModel.getRepoListPage()
        }
    }

    private val onWebChooserDialogItemClick: (UrlString) -> Unit = { urlString ->
        kotlin.runCatching {
            launchWebIntent(urlString)
        }.onFailure {
            showErrorToast("Page not found")
        }
    }
    //endregion


    //region OBSERVERS -----------------------------------------------------------------------------
    private fun setApiCallErrorObserver() {
        viewModel.apiCallError.observe(viewLifecycleOwner, EventObserver { errorText ->
            showErrorToast(errorText)
        })
    }

    private fun setDonutProgressObserver() {
        viewModel.donutProgress.observe(viewLifecycleOwner, EventObserver { isDonutProgressVisible ->
            binding.donutProgress.isVisible = isDonutProgressVisible
        })
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

    private fun showWebChooserDialog(repoEntity: RepoEntity) {
        WebChooserDialog.newInstance(
            repoEntity = repoEntity,
            onWebChooserDialogItemClick = onWebChooserDialogItemClick
        ).show(requireActivity().supportFragmentManager, WebChooserDialog::class.simpleName)
    }

    private fun launchWebIntent(urlString: UrlString) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlString)
        startActivity(intent)
    }

    private fun showErrorToast(errorText: String) {
        Toast.makeText(requireContext(), errorText, Toast.LENGTH_LONG).show()
    }
    //endregion

}