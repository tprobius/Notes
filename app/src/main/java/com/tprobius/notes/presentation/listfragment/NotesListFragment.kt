package com.tprobius.notes.presentation.listfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tprobius.notes.databinding.FragmentListBinding
import com.tprobius.notes.domain.model.Note
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: NotesListViewModel by viewModel()

    private lateinit var notesListAdapter: NotesListAdapter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel.getAllNotes()
//    }

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

        Log.d("WTF?!?!?!?", "${viewModel.getNotes()}")
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
        notesListAdapter = NotesListAdapter({}, {})
        binding.listRecyclerView.adapter = notesListAdapter
    }

    private fun setOnAddClick() {
        binding.floatingActionButton.setOnClickListener {
            viewModel.addNewNote()
        }
    }
}