package com.tprobius.notes.presentation.noteslistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.sidesheet.SideSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.tprobius.notes.R
import com.tprobius.notes.databinding.FragmentListBinding
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.presentation.noteslistfragment.noteslistadapter.NotesListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class NotesListFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentListBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: NotesListViewModel by viewModel()

    private var notesListAdapter by Delegates.notNull<NotesListAdapter>()

    private var recentlyDeletedNote by Delegates.notNull<Note>()

    private var spinnerValue by Delegates.notNull<String>()

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
        spinnerValue = ALL

        setHandleState()
        setNotesListAdapter()
        setOnFilterClick()
        setOnAddClick()
        setOnSettingsClick()
    }

    private fun setOnFilterClick() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.filterSpinner.adapter = adapter
            binding.filterSpinner.onItemSelectedListener = this@NotesListFragment
        }
    }

    private fun setHandleState() {
        viewModel.getNotesList(spinnerValue)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.observe(viewLifecycleOwner, ::handleState)
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
            notesListAdapter.submitList(
                when (sortingOrder) {
                    ASCENDING -> {
                        when (orderType) {
                            DATE -> notesList.sortedBy { it.title.lowercase() }
                            else -> notesList.sortedBy { it.timestamp }
                        }
                    }

                    else -> {
                        when (orderType) {
                            DATE -> notesList.sortedByDescending { it.title.lowercase() }
                            else -> notesList.sortedByDescending { it.timestamp }
                        }
                    }
                }
            )
        }
    }

    private fun showErrorState() {}

    private fun setNotesListAdapter() {
        notesListAdapter = NotesListAdapter(
            { note -> viewModel.editNote(note) },
            { note -> setOnFavoriteClick(note) },
            { note -> setOnDeleteClick(note) }
        )

        binding.listRecyclerView.adapter = notesListAdapter
    }

    private fun setOnFavoriteClick(note: Note) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.updateNote(note.id, !note.isFavorite)
            viewModel.getNotesList(spinnerValue)
        }
    }

    private fun setOnDeleteClick(note: Note) {
        recentlyDeletedNote = note

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deleteNote(note)
            viewModel.getNotesList(spinnerValue)
        }

        showSnackBar()
    }

    private fun showSnackBar() {
        Snackbar
            .make(binding.root, "Note was deleted", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                Snackbar.make(
                    binding.root,
                    "Note successfully restored",
                    Snackbar.LENGTH_SHORT
                ).show()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.restoreNote(recentlyDeletedNote)
                    viewModel.getNotesList(spinnerValue)
                }
            }.show()
    }

    private fun setOnAddClick() {
        binding.floatingActionButton.setOnClickListener {
            viewModel.addNewNote()
        }
    }

    private fun setOnSettingsClick() {
        binding.settingsImageButton.setOnClickListener {
            val sideSheetDialog = SideSheetDialog(requireContext())
            sideSheetDialog.setContentView(R.layout.fragment_settings)

            setOrderType(sideSheetDialog)
            setSortingOrder(sideSheetDialog)

            setOnOrderTypeClick(sideSheetDialog)
            setOnSortingOrderClick(sideSheetDialog)

            sideSheetDialog.show()
        }
    }

    private fun setOrderType(sideSheetDialog: SideSheetDialog) {
        when (orderType) {
            DATE -> sideSheetDialog.findViewById<RadioButton>(R.id.date_radioButton)?.isChecked =
                true

            else -> sideSheetDialog.findViewById<RadioButton>(R.id.title_radioButton)?.isChecked =
                true
        }
    }

    private fun setSortingOrder(sideSheetDialog: SideSheetDialog) {
        when (sortingOrder) {
            ASCENDING -> sideSheetDialog.findViewById<RadioButton>(R.id.ascending_radioButton)?.isChecked =
                true

            else -> sideSheetDialog.findViewById<RadioButton>(R.id.descending_radioButton)?.isChecked =
                true
        }
    }

    private fun setOnOrderTypeClick(sideSheetDialog: SideSheetDialog) {
        sideSheetDialog.findViewById<RadioButton>(R.id.date_radioButton)?.setOnClickListener {
            orderType = DATE
            viewModel.getNotesList(spinnerValue)
        }

        sideSheetDialog.findViewById<RadioButton>(R.id.title_radioButton)?.setOnClickListener {
            orderType = TITLE
            viewModel.getNotesList(spinnerValue)
        }
    }

    private fun setOnSortingOrderClick(sideSheetDialog: SideSheetDialog) {
        sideSheetDialog.findViewById<RadioButton>(R.id.ascending_radioButton)?.setOnClickListener {
            sortingOrder = ASCENDING
            viewModel.getNotesList(spinnerValue)
        }

        sideSheetDialog.findViewById<RadioButton>(R.id.descending_radioButton)?.setOnClickListener {
            sortingOrder = DESCENDING
            viewModel.getNotesList(spinnerValue)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 -> setOnAllFilter()
            else -> setOnFavoriteFilter()
        }
    }

    private fun setOnAllFilter() {
        spinnerValue = ALL
        viewModel.getNotesList(spinnerValue)
    }

    private fun setOnFavoriteFilter() {
        spinnerValue = FAVORITE
        viewModel.getNotesList(spinnerValue)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    companion object {
        const val ALL = "all"
        const val FAVORITE = "favorite"

        private const val DATE = "date"
        private const val TITLE = "title"

        private const val ASCENDING = "ascending"
        private const val DESCENDING = "descending"

        var orderType = TITLE
        var sortingOrder = ASCENDING
    }
}