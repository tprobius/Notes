package com.tprobius.notes.presentation.addnotefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tprobius.notes.databinding.FragmentNoteDetailBinding
import com.tprobius.notes.domain.model.Note
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: AddNoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHandleState()
        setOnSaveClick()
        setOnTryAgainClick()
    }

    private fun setHandleState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.observe(viewLifecycleOwner, ::handleState)
        }
    }

    private fun handleState(state: AddNoteState) {
        when (state) {
            AddNoteState.Initial -> showInitialState()
            AddNoteState.Loading -> showLoadingState()
            is AddNoteState.Success -> showSuccessState()
            AddNoteState.Error -> showErrorState()
        }
    }

    private fun showInitialState() {
        setViewsVisibility(
            titleEditTextVisibility = true,
            materialDividerVisibility = true,
            contentEditTextVisibility = true,
            saveFloatingActionButtonVisibility = true
        )
    }

    private fun showLoadingState() {
        setViewsVisibility(progressBarVisibility = true)
    }

    private fun showSuccessState() {
        setViewsVisibility()
    }

    private fun showErrorState() {
        setViewsVisibility(
            errorImageViewVisibility = true,
            errorTextViewVisibility = true,
            tryAgainButtonVisibility = true
        )
    }

    private fun setViewsVisibility(
        progressBarVisibility: Boolean = false,
        errorImageViewVisibility: Boolean = false,
        errorTextViewVisibility: Boolean = false,
        tryAgainButtonVisibility: Boolean = false,
        titleEditTextVisibility: Boolean = false,
        materialDividerVisibility: Boolean = false,
        contentEditTextVisibility: Boolean = false,
        saveFloatingActionButtonVisibility: Boolean = false
    ) {
        binding.progressBar.isVisible = progressBarVisibility
        binding.errorImageView.isVisible = errorImageViewVisibility
        binding.errorTextView.isVisible = errorTextViewVisibility
        binding.tryAgainButton.isVisible = tryAgainButtonVisibility
        binding.titleEditText.isVisible = titleEditTextVisibility
        binding.materialDivider.isVisible = materialDividerVisibility
        binding.contentEditText.isVisible = contentEditTextVisibility
        binding.saveFloatingActionButton.isVisible = saveFloatingActionButtonVisibility
    }

    private fun setOnSaveClick() {
        binding.saveFloatingActionButton.setOnClickListener {
            viewModel.addNewNote(
                Note(
                    id = 0,
                    title = binding.titleEditText.text.toString(),
                    content = binding.contentEditText.text.toString(),
                    isFavorite = false,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    private fun setOnTryAgainClick() {
        binding.tryAgainButton.setOnClickListener {
            setOnSaveClick()
        }
    }
}