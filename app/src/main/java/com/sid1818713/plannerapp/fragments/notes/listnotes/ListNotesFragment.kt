package com.sid1818713.plannerapp.fragments.notes.listnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.notedata.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list_notes.view.*

class ListNotesFragment : Fragment() {
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_notes, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.noteRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // NoteViewModel
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        view.noteFab.setOnClickListener {
            findNavController().navigate(R.id.action_listNotesFragment_to_addNotesFragment)
        }

        return view
    }
}