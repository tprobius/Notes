package com.tprobius.notes.presentation.addnotefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            is AddNoteState.Success -> showSuccessState(state.note)
            AddNoteState.Error -> showErrorState()
        }
    }

    private fun showInitialState() {}

    private fun showLoadingState() {}

    private fun showSuccessState(note: Note) {}

    private fun showErrorState() {}

    private fun setOnSaveClick() {
        binding.saveFloatingActionButton.setOnClickListener {
            viewModel.addNewNote(
                Note(
                    0,
                    binding.titleEditText.text.toString(),
                    binding.contentEditText.text.toString(),
                    false,
                    System.currentTimeMillis()
                )
            )
        }
    }
}