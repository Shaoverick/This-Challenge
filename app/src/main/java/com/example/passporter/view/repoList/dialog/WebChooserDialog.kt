package com.example.passporter.view.repoList.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.model.entity.RepoEntity
import com.example.domain.model.entity.UrlString
import com.example.passporter.databinding.DialogWebChooserBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WebChooserDialog : BottomSheetDialogFragment() {

    //region PROPERTIES ----------------------------------------------------------------------------
    private var _binding: DialogWebChooserBinding? = null
    private val binding get() = _binding!!

    private lateinit var repoEntity: RepoEntity
    private lateinit var onItemClick: (UrlString) -> (Unit)
    //endregion


    companion object {
        fun newInstance(
            repoEntity: RepoEntity,
            onWebChooserDialogItemClick: (UrlString) -> (Unit)
        ): WebChooserDialog {
            val dialog = WebChooserDialog()
            dialog.repoEntity = repoEntity
            dialog.onItemClick = onWebChooserDialogItemClick
            return dialog
        }
    }


    //region LIFECYCLE -----------------------------------------------------------------------------
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogWebChooserBinding.inflate(inflater, container, false)
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


    //region PRIVATE METHODS -----------------------------------------------------------------------
    private fun setLayout() {
        binding.tvWebOption1.setOnClickListener {
            onItemClick.invoke(repoEntity.htmlUrl)
            dismiss()
        }

        binding.tvWebOption2.setOnClickListener {
            onItemClick.invoke(repoEntity.ownerEntity.htmlUrl)
            dismiss()
        }
    }
    //endregion


    enum class WebChooserType {
        WEB,
        OWNER_WEB
    }

}