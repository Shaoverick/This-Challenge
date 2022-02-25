package com.example.passporter.view.repositoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.passporter.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : Fragment() {

    //region PROPERTIES ----------------------------------------------------------------------------
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RepoListVM by viewModels()
    //endregion


    //region LIFECYCLE ----------------------------------------------------------------------------
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRepoList(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

}