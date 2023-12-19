package com.tprobius.notes.presentation.editnotefragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        Log.d("WTF?!?!?!?!?!", "${note.id}")

        setHandleState()
        setOnSaveClick()
    }

    private fun setHandleState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                handleState(it)
            }
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

    private fun showInitialState() {}

    private fun showLoadingState() {}

    private fun showSuccessState(note: Note) {
        binding.titleEditText.setText(note.title)
        binding.contentEditText.setText(note.content)
    }

    private fun showErrorState() {}

    private fun setOnSaveClick() {
        binding.saveFloatingActionButton.setOnClickListener {
            viewModel.saveNote(
                Note(
                    note.id,
                    binding.titleEditText.text.toString(),
                    binding.contentEditText.text.toString()
                )
            )
        }
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