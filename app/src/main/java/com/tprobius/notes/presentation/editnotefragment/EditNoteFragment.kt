package com.tprobius.notes.presentation.editnotefragment

import android.os.Build
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

class EditNoteFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: EditNoteViewModel by viewModel()

    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.note as Note
        }
    }

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
        viewModel.getNoteById(note)
        setHandleState()
        setOnSaveClick()
    }

    private fun setHandleState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.observe(viewLifecycleOwner, ::handleState)
        }
    }

    private fun handleState(state: EditNoteState) {
        when (state) {
            EditNoteState.Initial -> showInitialState()
            EditNoteState.Loading -> showLoadingState()
            is EditNoteState.Success -> showSuccessState(state.note)
            EditNoteState.Error -> showErrorState()
        }
    }

    private fun showInitialState() {
        setViewsVisibility()
    }

    private fun showLoadingState() {
        setViewsVisibility(progressBarVisibility = true)
    }

    private fun showSuccessState(note: Note) {
        setViewsVisibility(
            titleEditTextVisibility = true,
            materialDividerVisibility = true,
            contentEditTextVisibility = true,
            saveFloatingActionButtonVisibility = true
        )

        binding.titleEditText.setText(note.title)
        binding.contentEditText.setText(note.content)
    }

    private fun showErrorState() {
        setViewsVisibility(
            errorImageViewVisibility = true,
            errorTextViewVisibility = true,
            tryAgainButtonVisibility = true
        )
    }

    private fun setOnSaveClick() {
        binding.saveFloatingActionButton.setOnClickListener {
            viewModel.saveNote(
                Note(
                    note.id,
                    binding.titleEditText.text.toString(),
                    binding.contentEditText.text.toString(),
                    note.isFavorite,
                    System.currentTimeMillis()
                )
            )
        }
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

    companion object {
        private const val NOTE = "note"

        private var Bundle.note
            get() =
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(
                        NOTE,
                        Note::class.java
                    )

                    else -> @Suppress("DEPRECATION") getParcelable(NOTE) as? Note
                }
            set(value) = putParcelable(NOTE, value)

        fun newInstance(note: Note) = EditNoteFragment().apply {
            arguments = Bundle().apply { this.note = note }
        }
    }
}