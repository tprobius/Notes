package com.tprobius.notes.presentation.noteslistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.tprobius.notes.databinding.FragmentListBinding
import com.tprobius.notes.domain.model.Note
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class NotesListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: NotesListViewModel by viewModel()

    private var notesListAdapter by Delegates.notNull<NotesListAdapter>()

    private var recentlyDeletedNote by Delegates.notNull<Note>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHandleState()
        setNotesListAdapter()
        setOnAddClick()
    }

    private fun setHandleState() {
        viewModel.getAllNotes()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                handleState(it)
            }
        }
    }

    private fun handleState(state: NotesListState) {
        when (state) {
            NotesListState.Initial -> showInitialState()
            NotesListState.Loading -> showLoadingState()
            is NotesListState.Success -> showSuccessState(state.notesList)
            NotesListState.Error -> showErrorState()
        }
    }

    private fun showInitialState() {}

    private fun showLoadingState() {}

    private fun showSuccessState(notesList: List<Note>) {
        viewLifecycleOwner.lifecycleScope.launch {
            notesListAdapter.submitList(notesList)
        }
    }

    private fun showErrorState() {}

    private fun setNotesListAdapter() {
        notesListAdapter = NotesListAdapter({
            viewModel.editNote(it)
        }, {
            viewModel.updateNote(it)
        }, {
            recentlyDeletedNote = it

            viewModel.deleteNote(it)

            Snackbar
                .make(binding.root, "Note was deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    Snackbar.make(
                        binding.root,
                        "Note successfully restored",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    viewModel.restoreNote(recentlyDeletedNote)
                }.show()
        })

        binding.listRecyclerView.adapter = notesListAdapter
    }

    private fun setOnAddClick() {
        binding.floatingActionButton.setOnClickListener {
            viewModel.addNewNote()
        }
    }
}