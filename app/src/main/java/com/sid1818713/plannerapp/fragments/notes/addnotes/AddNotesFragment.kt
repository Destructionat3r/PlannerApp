package com.sid1818713.plannerapp.fragments.notes.addnotes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.notedata.model.Note
import com.sid1818713.plannerapp.notedata.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_notes.*
import kotlinx.android.synthetic.main.fragment_add_notes.view.*

class AddNotesFragment : Fragment() {
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_notes, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.addNote_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val noteTitle = addNoteTitle_et.text.toString()
        val noteContents = addNoteContents_et.text.toString()

        if(inputCheck(noteTitle, noteContents)) {
            //Create Note Object
            val note = Note(0, noteTitle, noteContents)

            //Add Data to Database
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Note added!", Toast.LENGTH_SHORT).show()

            //Navigate Back
            findNavController().navigate(R.id.action_addNotesFragment_to_listNotesFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(noteTitle: String, noteContents: String): Boolean {
        return !(TextUtils.isEmpty(noteTitle) || TextUtils.isEmpty(noteContents))
    }
}